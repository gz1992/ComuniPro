package comunicacao;

//taxa de descarte
public class TD {
	private static int taxa;
	public synchronized static void set(int t) { taxa = t; }
	public synchronized static int get() {return taxa; }
}
