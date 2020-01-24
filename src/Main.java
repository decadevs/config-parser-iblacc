
public class Main {

	public static void main(String[] args) {
		String path = "src/config.txt";
		if(args.length > 0) {
			path = args[0];
		}
		
		ConfigParser config = new ConfigParser(path);
		System.out.println(config.get("application.name"));
		System.out.println(config.all());

	}
}

