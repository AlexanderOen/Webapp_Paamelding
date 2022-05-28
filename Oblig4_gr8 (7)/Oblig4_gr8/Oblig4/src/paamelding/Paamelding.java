package paamelding;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.EJB;
import database.Deltager;
import database.DeltagerDAO;
import validering.Validator;

@WebServlet("/Paamelding")
public class Paamelding extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Validator validator = new Validator();
	
	@EJB
	private DeltagerDAO deltagerDAO;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		boolean feil = true;
		Cookie loggetinn = null;
		
		try {
			loggetinn = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn")).findAny().get();
		}
		catch(Throwable e) {}
		
		try {
			feil = (Boolean)request.getSession().getAttribute("feil");
		}
		catch(Throwable e) {}
		
		if(feil || loggetinn == null) {
			request.getRequestDispatcher("WEB-INF/paameldingsskjema.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/paameldingsbekreftelse.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");
		String passordRepetert = request.getParameter("passordRepetert");
		String kjonn = request.getParameter("kjonn");
		
		request.getSession().invalidate();
		
		request.getSession().setAttribute("fornavn", fornavn);
		request.getSession().setAttribute("etternavn", etternavn);
		request.getSession().setAttribute("mobil", mobil);
		request.getSession().setAttribute("passord", passord);
		request.getSession().setAttribute("passordRepetert", passordRepetert);
		request.getSession().setAttribute("kjonn", kjonn);
		
		Boolean feil = false;
		
		if(!validator.validerFornavn(fornavn)) {
			request.getSession().setAttribute("fornavnFeilmelding", "Ugyldig fornavn");
			request.getSession().setAttribute("fornavn", "");
			feil = true;
		}
		
		if(!validator.validerEtternavn(etternavn)) {
			request.getSession().setAttribute("etternavnFeilmelding", "Ugyldig etternavn");
			request.getSession().setAttribute("etternavn", "");
			feil = true;
		}
		
		if(!validator.validerMobil(mobil)) {
			request.getSession().setAttribute("mobilFeilmelding", "Ugyldig mobilnummer");
			request.getSession().setAttribute("mobil", "");
			feil = true;
		}
		
		if(!validator.validerPassord(passord)) {
			request.getSession().setAttribute("passordFeilmelding", "For svakt passord");
			request.getSession().setAttribute("passord", "");
			feil = true;
		}
		
		if(!validator.validerPassordRepetert(passordRepetert, passord)) {
			request.getSession().setAttribute("passordRepetertFeilmelding", "Passord m&aring; v&aelig;re like");
			request.getSession().setAttribute("passordRepetert", "");
			feil = true;
		}
		
		if(!validator.validerKjonn(kjonn)) {
			request.getSession().setAttribute("kjonnFeilmelding", "Må velge kj&oslash;nn");
			request.getSession().setAttribute("mobil", "");
			feil = true;
		}
		
		if(!feil) {
			Deltager nyDeltager = new Deltager(fornavn,etternavn,Integer.parseInt(mobil),passord,kjonn);

			try {
				deltagerDAO.lagreNyDeltager(nyDeltager);
				Cookie innlogget = new Cookie("brukernavn",mobil);
				innlogget.setMaxAge(300);
				response.addCookie(innlogget);
			}
			catch(Throwable e) {
				feil = true;
				request.getSession().setAttribute("mobilFeilmelding", "Deltager med dette nummeret er allerede registrert");
				request.getSession().setAttribute("mobil", "");
			}
		}
		
		request.getSession().setAttribute("feil", feil);
		response.sendRedirect("Paamelding");
	}

}
