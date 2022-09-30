package Controller;

import Helper.DbConnector;
import Model.Candidate;

import java.sql.*;
import java.util.ArrayList;

public class CandidateController {

    public void listAllCandidates()
    {
        ArrayList<Candidate> candidates = new ArrayList<>();
        try (Connection conn = DbConnector.getConnection()){
            String sql = "SELECT * FROM candidates";
            PreparedStatement pmst = conn.prepareStatement(sql);
            preparedStatement(candidates, pmst);

        }catch (SQLException sqx) {
            System.out.println(sqx.getMessage());
        }

    }

    public void getCandidateById(int id) {
        ArrayList<Candidate> candidates = new ArrayList<>();
        try (Connection conn = DbConnector.getConnection()){
            String sql = "SELECT * FROM candidates where id =  ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            preparedStatement(candidates, stmt);

        }catch (SQLException sqx) {
            System.out.println(sqx.getMessage());
        }


    }

    private static void preparedStatement(ArrayList<Candidate> candidates, PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();

        Candidate candidate = null;

        while (rs.next()) {

            candidate = new Candidate();

            candidate.setId(rs.getInt("id"));
            candidate.setFirstName(rs.getString("first_name"));
            candidate.setLastName(rs.getString("last_name"));
            candidate.setDateOfBirth(rs.getDate("dob"));
            candidate.setEmail(rs.getString("email"));
            candidates.add(candidate);

        }
        for (Candidate list: candidates) {
            System.out.print(list.getId() + " " + list.getFirstName() + " " + list.getLastName() +
                    " " + list.getDateOfBirth() + " " + list.getEmail());
            System.out.println();
        }
    }


}
