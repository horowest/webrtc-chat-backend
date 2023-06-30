package com.akash.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.akash.dto.ReponseTO;
import com.akash.dto.RequestTO;
import com.akash.dto.SessionDescriptionProtocol;
import com.akash.service.ConnectionService;
import com.akash.util.JsonUtil;

@WebServlet("/connect")
public class ConnectionServlet extends HttpServlet {

    private ConnectionService connectionService = ConnectionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String otherUsername = req.getParameter("otherUsername");

        SessionDescriptionProtocol sdp = connectionService.getAnswer(username);

        ReponseTO reponseTO;
        if(sdp != null) {
            reponseTO = new ReponseTO(true, otherUsername, sdp);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            reponseTO = new ReponseTO(false, null, null);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        resp.setContentType("application/json");
        resp.getWriter().print(JsonUtil.stringyfy(reponseTO));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        RequestTO requestTO = JsonUtil.parse(json);

        connectionService.answerOffer(requestTO.getOtherUsername(), requestTO.getKey());

        ReponseTO reponseTO = new ReponseTO(false, null, null);

        resp.setContentType("application/json");
        resp.getWriter().print(JsonUtil.stringyfy(reponseTO));
    }
    
}
