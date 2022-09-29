package Database;

import Model.Candidate;
import org.checkerframework.checker.units.qual.C;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbQueries {

    public void listAllCandidates()
    {
        ArrayList<Candidate> candidates = new ArrayList<>();
        try (Connection conn = DbConnector.getConnection()){
            String sql = "SELECT * FROM candidates";
            PreparedStatement pmst = conn.prepareStatement(sql);
            ResultSet rs = pmst.executeQuery();

            Candidate candidate = null;

            while (rs.next()) {

                candidate = new Candidate();

                candidate.setId(rs.getString("id"));
                candidate.setFirstName(rs.getString("first_name"));
                candidate.setLastName(rs.getString("last_name"));
                candidate.setDateOfBirth(rs.getString("dob"));
                candidate.setEmail(rs.getString("email"));
                candidates.add(candidate);

            }
            for (Candidate list: candidates) {
                System.out.print(list.getId() + " " + list.getFirstName() + " " + list.getLastName() +
                        " " + list.getDateOfBirth() + " " + list.getEmail());
                System.out.println();
            }

        }catch (SQLException sqx) {
            System.out.println(sqx.getMessage());
        }

    }

    public void getCandidate(int id) {

    }


}
