package logg;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.ejb.EJB;

import database.Deltager;
import database.DeltagerDAO;
import validering.BCrypt;
import validering.Validator;

@WebServlet("/Logginn")
public class LoggInn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Validator validator = new Validator();
	
	@EJB
	private DeltagerDAO deltagerDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String feil = (String)request.getSession().getAttribute("logginnfeil");
		
		if(feil != null) {
			request.getSession().invalidate();
			List<String> feilmeldinger = Arrays.asList("Ugyldig brukernavn og/eller passord");
			request.setAttribute("feilmeldinger", feilmeldinger);
		}
		
		request.getRequestDispatcher("WEB-INF/logginn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean logginn = false;
		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");
		Deltager deltager = null;
		
		if(validator.validerMobil(mobil) && validator.validerPassord(passord)) {
			deltager = deltagerDAO.hentDeltager(Integer.parseInt(mobil));
		}
		
		if(deltager != null) {
			try {
				logginn = BCrypt.checkpw(passord, deltager.getPassord());
			} 
			catch (IllegalArgumentException e) {				
			}
		}
		
		if(logginn) {
			Cookie innlogget = new Cookie("brukernavn",mobil);
			innlogget.setMaxAge(300);
			response.addCookie(innlogget);
			response.sendRedirect("Deltagerliste");
		}
		else {
			request.getSession().setAttribute("logginnfeil", "true");
			response.sendRedirect("Logginn");
		}
	}
	
}
