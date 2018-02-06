package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsynServlet extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public AsynServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Servlet执行开始时间:" + new Date());
        AsyncContext context = request.startAsync();
        new Thread(new Executor(context)).start();
//		request.getRequestDispatcher(request.getContextPath() + "/async.jsp").forward(request,response);
        System.out.println("Servlet执行结束时间:" + new Date());
        System.out.println(Thread.currentThread().hashCode());
    }

    public class Executor implements Runnable {
        private AsyncContext context;

        public Executor(AsyncContext context) {
            this.context = context;
        }

        @Override
        public void run() {
            //执行相关复杂业务
            try {
                Thread.sleep(1000 * 10);
//				context.getRequest();
//				context.getResponse();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                request.getRequestDispatcher(request.getContextPath() + "/async.jsp").forward(request, response);
                System.out.println(Thread.currentThread().hashCode());
                context.complete();
                System.out.println("业务执行完成时间:" + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}