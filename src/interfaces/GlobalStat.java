package interfaces;

public class GlobalStat {
private static String username="";
public static void setUsername(String usr) {
	username=usr;
}
public static String getUsername() {
	return username;
}

}
