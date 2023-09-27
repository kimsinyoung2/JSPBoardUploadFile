package cs.dit.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BListService implements boardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 1. BoardDao 생성
		BoardDao dao = new BoardDao();
		int count = dao.recordCount(); // 전체 게시글(레코드) 수
		int numOfRecords = 10; // 한번에 가져오는 개수 
		int numOfPages = 5; // 한 화면에표시할 개수
		
		String page_ = request.getParameter("p");
		int p = 1;
		
		if(page_ != null && !page_.equals(""))
			p = Integer.parseInt(page_);
		
		ArrayList<BoardDto> dtos = dao.list(p, numOfRecords);
		
		// 페이지 번호 값 계산 
		int startNum = p-((p-1)%numOfPages);
		
		// 마지막 출력 페이지 값 계싼
		int lastNum = (int)Math.ceil(((float)count/(float)numOfRecords));		

		
		//  DB에서 검색해온 dtos 값을 저장하는 호출 결과를 처리한다.
		request.setAttribute("boardDtos", dtos);
		request.setAttribute("p", p);
		request.setAttribute("startNum", startNum);
		request.setAttribute("lastNum", lastNum);
		request.setAttribute("numOfPages", numOfPages);

		System.out.println("p =" + p);
		System.out.println("startNum = " + startNum);
		System.out.println("lastNum =" + lastNum);

	}

}
