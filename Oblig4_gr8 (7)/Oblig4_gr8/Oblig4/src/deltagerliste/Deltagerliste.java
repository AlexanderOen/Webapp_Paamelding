package deltagerliste;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.Deltager;
import database.DeltagerDAO;

@WebServlet("/Deltagerliste")
public class Deltagerliste extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DeltagerDAO deltagerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		List<Deltager> deltagere = deltagerDAO.hentAlleDeltagere();
		Cookie innlogget = null;
		
		try {
			innlogget = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn")).findAny().get();
		}
		catch(NoSuchElementException e) {}
		
		if(innlogget == null) {
			response.sendRedirect("Paamelding");
		}
		else {
			if(deltagere != null && !deltagere.isEmpty()) {
				deltagere.sort(new Comparator<Deltager>() {
					@Override
					public int compare(Deltager f1, Deltager f2) {
						int c1 = f1.getFornavn().compareTo(f2.getFornavn());
						return c1 == 0 ? f1.getEtternavn().compareTo(f2.getEtternavn()) : c1;
					}
				});
			}
			
			request.setAttribute("brukernavn", innlogget.getValue());
			request.setAttribute("deltagere", deltagere);
			request.getRequestDispatcher("WEB-INF/deltagerliste.jsp").forward(request, response);
		}
	}

}
