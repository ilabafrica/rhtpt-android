package pt.rht.Api;

/**
 * Created by Kitsao on 12/04/2017.
 */

public class Rest {
    // Base URL
    private static final String API_URL = "http://10.0.2.2:8000/api/v1/";

    // Auth
    public static final String AUTH_URL = API_URL + "auth";
    // Register
    public static final String REGISTER_URL = API_URL + "register";
    // User
    public static final String USER_URL = API_URL + "user";
    // Enrolment
    public static final String ENROLMENT_URL = API_URL + "enrolment";
    // Facility
    public static final String FACILITY_URL = API_URL + "facility";
    // Field
    public static final String FIELD_URL = API_URL + "field";
    // Set
    public static final String SET_URL = API_URL + "set";
    // Option
    public static final String OPTION_URL = API_URL + "option";
    // Panel
    public static final String PANEL_URL = API_URL + "panel";
    // Failure
    public static final String FAILURE_URL = API_URL + "failure";
    // Designation
    public static final String DESIGNATION_URL = API_URL + "designation";
    // Round
    public static final String ROUND_URL = API_URL + "round";
    // Pt
    public static final String PT_URL = API_URL + "pt";
    // Result
    public static final String RESULT_URL = API_URL + "result";
    // Feedback
    public static final String FEEDBACK_URL = API_URL + "feedback";
    // Field-Option
    public static final String FO_URL = API_URL + "fo";
}