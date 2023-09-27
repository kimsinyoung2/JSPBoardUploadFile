package cs.dit.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 50
		)
@WebServlet("*.do")
public class boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String ViewPage = null;
	
		
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do"));
		System.out.println(com);
		
		if(com != null && com.trim().equals("list")) {
			boardService service = new BListService();
			service.execute(request, response);
			ViewPage = "/WEB-INF/view/list.jsp";
		}  else if (com != null && com.trim().equals("insertForm")) {
			ViewPage = "/WEB-INF/view/insertForm.jsp";
		}else if (com != null && com.trim().equals("insert")) {
			boardService service = new BInsertService();
			service.execute(request, response);
			ViewPage = "/WEB-INF/view/list.do";
		}else if (com != null && com.trim().equals("updateForm")) {
			boardService service = new BSelectOneService();
			service.execute(request, response);
			ViewPage = "/WEB-INF/view/updateForm.jsp";
		} else if (com != null && com.trim().equals("update")) {
			boardService service = new BUpdateService();
			service.execute(request, response);
			ViewPage = "/WEB-INF/view/list.do";
		} else if (com != null && com.trim().equals("delete")) {
			boardService service = new BDeleteService();
			service.execute(request, response);
			ViewPage = "/WEB-INF/view/list.do";
		}
		RequestDispatcher rd = request.getRequestDispatcher(ViewPage);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
