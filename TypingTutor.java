package GameTypingTutor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {

	private JLabel lbltimer;
	private JLabel lblscore;
	private JLabel lblword;
	private JTextField txtword;
	private JButton startbutton;
	private JButton stopbutton;

	private static final String INPUT_FOLDER_BASE_ADDRESS = "/Users/ayush/Desktop/";

	private int timeleft;
	private int score;
	private boolean running;
	private String[] data;
	private Timer timer;
	private int TotalWordsDisplayed;
	private int TotalWordsTypedCorrectly;
	private String feeder = null;
	// private String hardFeeder;
	// private String medFeeder;
	// private String easyFeeder;

	private String[] hardFeeder = { "pizzazzy", "zyzzyvas", "jazzlike", "quizzing", "zizzling", "jacuzzis", "pizazzes",
			"pazazzes", "pizzazes", "bezazzes", "quizzers", "jazziest", "zugzwang", "buzzwigs", "zigzaggy", "bemuzzle",
			"whizzing", "muzzling", "buzzcuts", "puzzling", "buzzword", "fizzling", "mizzling", "unpuzzle", "unmuzzle",
			"guzzling", "embezzle", "huzzahed", "chazzens", "huzzaing", "jumbucks", "dizzying", "mezuzahs", "jipijapa",
			"frizzily", "frizzing", "chazzans", "hazzanim", "nuzzling", "mezuzoth", "jujuisms", "jejunely", "jambeaux",
			"skipjack", "whizzier", "jacquard", "swizzled", "flapjack", "dazzling", "frazzled", "blizzard", "frizzled",
			"puzzlers", "fuzztone", "buzzards", "pizzelle", "quincunx", "whizzers", "pozzolan", "bedazzle", "muezzins",
			"muzzlers", "grizzled", "mezquite", "mezquits", "japanize", "beziques", "sizzling", "quadplex", "frizzles",
			"bejeezus", "fuzziest", "scuzzier", "highjack", "frizzler", "skyjacks", "frazzles", "swizzler", "bezzants",
			"guzzlers", "albizzia", "zabajone", "swizzles", "jarovize", "jumpoffs", "kazachok", "caziques", "zwieback",
			"mazzards", "maximize", "kolkhozy", "muzziest", "oxazepam", "palazzos", "kuvaszok", "jezebels", "kazachki",
			"jejunity", "banjaxed", "quillaja", "mozzetta", "kickback", "drizzled", "janizary", "hizzoner", "mozzette",
			"grizzles", "vizcacha", "grizzler", "nuzzlers", "frizzies", "czaritza", "gizzards", "pizzeria", "fizziest",
			"frizzers", "sovkhozy", "jiujutsu", "jackfish", "bluejack", "frizzier", "jonquils", "jujutsus", "equalize",
			"quixotic", "dazzlers", "slapjack", "tzitzith", "ozonized", "quetzals", "zarzuela", "lockjaws", "zincking",
			"muckluck", "jackdaws", "jumpable", "longjump", "jiujitsu", "quezales", "paxwaxes", "quacking", "backpack",
			"azulejos", "jemmying", "jujuists", "chutzpah", "banjaxes", "whizbang", "jujitsus", "bejumble", "kamikaze",
			"drizzles", "jumbling", "schmalzy", "pickwick", "quackism", "quantize", "jimmying", "squeezed", "hijacked",
			"schmoozy", "jacklegs", "exorcize", "dizziest", "azotized", "kinkajou", "jockeyed", "khazenim", "piquancy",
			"ozonizer", "carjacks", "zymogram", "quartzes", "bootjack", "cyclized", "quipping", "zymology", "squeezer",
			"benzylic", "kazatsky", "joyfully", "jammable", "quackish", "mahjongg", "quackery", "jokingly", "zucchini",
			"quaffing", "gazumped", "chipmuck", "squeezes", "gazpacho", "jackboot", "blowzily", "humpback", "snazzier",
			"queazier", "ozonizes", "quixotry", "quatorze", "jaywalks", "sjamboks", "jackpots", "coxcombs", "sizzlers",
			"zecchins", "zecchino", "coccyxes", "terrazzo", "picklock", "zecchini", "zelkovas", "jibingly", "chutzpas",
			"upgazing", "mitzvahs", "conjunct", "comprize", "frowzily", "convexly", "zombiism", "popinjay", "bobbysox",
			"kebbucks", "exequial", "lunchbox", "oxidized", "quibbled", "exemplum", "jackroll", "tzaritza", "zikkurat",
			"kabeljou", "kolkozes", "jackstay", "schmelze", "polyzoic", "checkoff", "mitzvoth", "schmaltz", "japingly",
			"pirozhki", "quickens", "cachexic", "unzipped", "juggling", "mbaqanga", "unjammed", "myxameba", "hummocky",
			"bouzouki", "matchbox", "snuffbox", "azotizes", "equivoke", "dummkopf", "chazanim", "cyclizes", "pickaxed",
			"zaddikim", "squelchy", "jocundly", "jibbooms", "unjoyful", "biconvex", "quippish", "kumquats", "pirozhok",
			"squawked", "numchuck", "abjectly", "exahertz", "hijacker", "coenzyme", "knockoff", "bombyxes", "junkyard",
			"gazumper", "muntjaks", "joystick", "boychick", "zeppelin", "jauncing", "gimmicky", "pyxidium", "exequies",
			"backflip", "quaylike", "cliquing", "backflow", "colloquy", "mazeltov", "cobwebby", "lysozyme", "quackier",
			"bushbuck", "enjambed", "nudzhing", "brunizem", "imblazed", };
	private String[] medFeeder = { "pazazz", "pizazz", "pizzaz", "jazzbo", "bezazz", "jazzed", "zizzle", "jazzes",
			"jazzer", "muzjik", "whizzy", "mizzly", "scuzzy", "fuzzed", "puzzle", "muzzle", "buzzed", "huzzah",
			"frizzy", "jujube", "mizzen", "fizzed", "fuzzes", "fizzle", "mizzle", "mezuza", "zigzag", "buzzes",
			"buzzer", "pizzle", "guzzle", "fezzed", "wizzen", "hazzan", "fezzes", "wizzes", "bizzes", "cozzes",
			"fizzer", "fizzes", "huzzas", "queazy", "nuzzle", "mezzos", "snazzy", "jojoba", "piazza", "pizzas",
			"piazze", "banjax", "zizith", "jejune", "dazzle", "bijoux", "paxwax", "nozzle", "hajjes", "quezal",
			"hajjis", "jejuna", "quartz", "zincky", "razzed", "jezail", "zanzas", "exequy", "izzard", "zebeck",
			"quacky", "zazens", "sizzle", "muzhik", "quokka", "razzes", "quippy", "rozzer", "jockey", "tzetze",
			"kuvasz", "kolkoz", "jubbah", "jinxed", "hijack", "buzuki", "bombyx", "tazzas", "jimply", "coccyx",
			"blowzy", "gazump", "jumble", "syzygy", "jacked", "jackal", "joypop", "jubhah", "frowzy", "pyjama",
			"jumped", "juking", "klutzy", "jugful", "quippu", "moujik", "mujiks", "quicks", "squawk", "jinxes",
			"maxixe", "pickax", "quacks", "jumbal", "kwanza", "hamzah", "jingko", "jabbed", "crojik", "zygoma",
			"quench", "jiving", "jokily", "jumper", "khazen", "quiffs", "benzyl", "jugums", "zapped", "jungly",
			"commix", "jambed", "fizgig", "wheezy", "quirky", "cliquy", "jiggly", "bombax", "zipped", "kolhoz",
			"boojum", "skybox", "junked", "yakuza", "joking", "jacket", "quaggy", "cowpox", "jammed", "schizy",
			"mezcal", "qualmy", "quaffs", "kopjes", "jibbed", "zincic", "jouncy", "jockos", "volvox", "kudzus",
			"fuzing", "jobbed", "kibitz", "mazuma", "zephyr", "jumbos", "joyful", "jacker", "jarvey", "jimmie",
			"jinked", "jimper", "zombie", "cozily", "jingly", "quaked", "pujahs", "fazing", "pulque", "jauked",
			"jobber", "jocund", "jayvee", "object", "qubyte", "johnny", "frenzy", "buqsha", "zombis", "quaich",
			"byzant", "mamzer", "jowing", "junket", "quiche", "enzyme", "enzyms", "logjam", "jouked", "myxoma",
			"hutzpa", "japing", "judoka", "jabber", "convex", "jalopy", "lummox", "jammer", "frouzy", "muskox",
			"schnoz", "pajama", "adzuki", "jicama", "chivvy", "chintz", "zebecs", "floozy", "boxful", "jangly",
			"zeugma", "zydeco", "cheque", "zonked", "chazan", "zechin", "jibing", "mazily", "mazing", "jibber",
			"nazify", "zapper", "zaddik", "jawing", "junker", "zaffar", "eczema", "junkie", "juggle", "jiminy",
			"abject", "kickup", "momzer", "veejay", "sphynx", "efflux", "zaffer", "quitch", "jugged", "bronzy",
			"upgaze", "kanzus", "zipper", "zaffre", "chucky", "jambes", "afflux", "pegbox", "zaffir", "djinny",
			"gazabo", "zouave", "qualms", "kopeck", "blazed", "quorum", "quirks", "quiver", "quohog", "hamzas",
			"mixups", "quipus", "quinic", "quince", "plaque", "wurzel", "blintz", "schizo", "joggle", "gazebo",
			"jogged", "quaver", "gazing", "mizuna", "qwerty", "jupons", "quarks", "climax", "jigged", "whacky",
			"japery", "glitzy", "benzol", "benzal", "benzin", "jiggle", "jigsaw", "jerked", "chukka", "chummy",
			"wheeze", "chuffy", };
	private String[] easyFeeder = { "jazz", "buzz", "fuzz", "fizz", "hajj", "juju", "quiz", "razz", "jeez", "jeux",
			"jinx", "jock", "jack", "jump", "jamb", "juku", "joky", "jivy", "jiff", "junk", "jimp", "jibb", "jauk",
			"phiz", "zouk", "zonk", "juke", "chez", "cozy", "zyme", "mazy", "jouk", "qoph", "jink", "whiz", "fozy",
			"joke", "jake", "zebu", "java", "fuji", "jowl", "puja", "jerk", "jaup", "jive", "jagg", "zeks", "jupe",
			"fuze", "putz", "hazy", "koji", "zinc", "futz", "juba", "zerk", "juco", "jube", "quip", "waxy", "jehu",
			"bozo", "mozo", "jugs", "jows", "jeep", "dozy", "lazy", "jefe", "flux", "maze", "czar", "faze", "pixy",
			"meze", "john", "boxy", "jibe", "juga", "jibs", "bize", "jury", "jobs", "prez", "jabs", "friz", "jape",
			"poxy", "zeps", "jams", "quay", "zany", "yutz", "zaps", "quey", "zarf", "mojo", "quag", "hadj", "zoic",
			"foxy", "jaws", "zoom", "zips", "zing", "brux", "falx", "keck", "calx", "vext", "minx", "faux", "oyez",
			"jogs", "quod", "crux", "jags", "joys", "quin", "judo", "zigs", "zags", "kick", "zill", "gaze", "buck",
			"lynx", "josh", "joey", "haze", "puck", "plex", "jigs", "flex", "oozy", "haji", "muck", "sizy", "jell",
			"djin", "quid", "jill", "jays", "lutz", "jinn", "quad", "geez", "flax", "mock", "prex", "pump", "azon",
			"muff", "maxi", "azan", "huck", "back", "dojo", "bumf", "exam", "wack", "exec", "kemp", "bump", "guck",
			"vamp", "zoon", "mumm", "dexy", "mump", "nixy", "kyak", "zone", "wick", "pack", "wych", "peck", "oxim",
			"ditz", "mack", "daze", "doxy", "ibex", "doze", "buff", "laze", "quit", "beck", "nazi", "adze", "apex",
			"aqua", "jins", "fixt", "coxa", "zeal", "expo", "cuff", "zein", "jolt", "jole", "zeds", "quai", "jilt",
			"jeon", "yuck", "pock", "puff", "jeed", "coax", "qaid", "jane", "qadi", "jarl", "suqs", "bock", "jail",
			"join", "feck", "onyx", "jura", "cock", "zins", "jean", "jute", "moxa", "zona", "ouzo", "juts", "zine",
			"just", "jade", "pick", "mixt", "qats", "oryx", "comb", "ooze", "cwms", "coff", "womb", "raja", "orzo",
			"hick", "cobb", "sexy", "wavy", "boff", "size", "hock", "comp", "biff", "chum", "much", "heck", "miff",
			"luxe", "chub", "bomb", "coky", "bevy", "hoax", "bibb", "luck", "raze", "howk", "caky", "pomp", "kaph",
			"jato", "waff", "bunk", "pfft", "caff", "camp", "upby", "icky", "fumy", "kink", "khaf", "baff", "jars",
			"bulk", "xyst", "yack", "yock", "gowk", "jees", "jiao", "jets", "jest", "jete", "joes", "jeer", "jots",
			"hack", "tzar", "jota", "joss", "Word", "pimp", "poky", "wimp", "gawk", "zoos", "izar", "zori", "fyke",
			"soja", "zoea", "duck", "hawk", "hump", "ritz", "vugh", "vugg", "huff", "guff", "doux", "jess", "zits",
			"funk", "zees", "geck", "cavy", "ajar", "ajee", "klik", "ziti", "punk", "whup", "zeta", "zero", "zest",
			"koph", "konk", "nock", "nixe", "calk", "chug", "mugg", "chap", "cham", "wich", "chow", "chaw", "chip",
			"chic", "chew", "chop", "chef", "whop", "mumu", "next", "whom", "club", "nick", "roux", "ruck", "neck",
			"murk", "musk", "mycs", "caph", "mopy", "knew", "kiva", "knap", "gawp", "kirk", "vavs", "kelp", "iffy",
			"emmy", "busk", "knob", "kook", "kufi", "kvas", "hymn", "eaux", "knop", "know", "ilex", "exed", "folk",
			"flub", "flak", "fyce", "tuck", "gaff", "gamb", "fink", "typp", "kava", "keek", "exon", "kaki", "kaka",
			"gamp", "Word", "immy", "dump", "dumb", "cyme", "cyma", "cusk", "whap", "wham", "mach", "deck", "dawk",
			"culm", "cuke", "whim", "whip", "conk", "whew", "cowy", "milk", "mink", "lych", "dick", "hunk", "hulk",
			"lick", "vive", "viva", "lack", "duff", "howf", "walk", "luff", "lump", "wauk", "suck", "gimp", "dock",
			"lock", "monk", "copy", "axon", "blub", "puke", "pipy", "bonk", "axle", "pacy", "gyve", "baby", "bach",
			"oxen", "oxid", "bulb", "pink", "yawp", "wink", "axed", "axal", "pulp", "pupu", "axel", "phew", "numb",
			"hemp", "bubu", "axil", "yaff", "pech", "bilk", "wonk", "yuch", "bank", "balk", "gunk", "pyic", "pugh",
			"ovum", "plum", "pawn", "gulp", "pawl", "pung", "hype", "wove", "kobo", "beak", "sock", "vows", "gulf",
			"view", "bask", "kiwi", "peke", "verb", "hypo", "pave", "dunk", "pupa", "kobs", "laky", };

	private String[] difficultyStrArr = { "Easy", "Medium", "Hard" };
	private String difficulty;

	public TypingTutor() throws IOException {

		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);

		Font font = new Font("Algerian", 3, 60);

		lbltimer = new JLabel();
		lbltimer.setText("Timer  :");
		lbltimer.setBackground(Color.black);
		;
		lbltimer.setFont(font);
		super.add(lbltimer);

		lblscore = new JLabel();
		lblscore.setText("SCORE  :");
		lblscore.setBackground(Color.BLACK);
		lblscore.setFont(font);
		super.add(lblscore);

		lblword = new JLabel();
		lblword.setText(" ");
		lblword.setOpaque(true);
		lblword.setBackground(Color.GREEN);
		lblword.setFont(font);
		super.add(lblword);

		txtword = new JTextField();
		txtword.setText("");
		txtword.setFont(font);
		super.add(txtword);

		startbutton = new JButton();
		startbutton.setText("*START*");
		startbutton.setFont(font);
		startbutton.addActionListener(this);
		super.add(startbutton);

		stopbutton = new JButton();
		stopbutton.setText("*STOP*");
		stopbutton.setFont(font);
		stopbutton.addActionListener(this);
		super.add(stopbutton);
		// stopbutton.setIcon(defaultIcon);

		super.setSize(1000, 1000);
		super.setTitle(" TYPING MASTER ");

		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JOptionPane.showMessageDialog(this, "RULES:-" + " This game has 3 difficulty Levels i.e. Easy, Medium, Hard."
				+ "\n" + "\n"
				+ "If you start from a lower difficulty level, eventually you will be promoted as you type more words correctly."
				+ "\n" + "As the Difficulty level Increases the Length of the word to be typed Will also increase."
				+ "\n" + "\n" + "YOU WILL BE GIVEN 2.5 seconds TO TYPE ANY WORD." + "\n" + "\n"
				+ "* How will you be promoted from Easy Difficulty level to Medium Difficulty Level :-" + "\n"
				+ "You have to attempt to type atleast 12 words ,  with not more than 2 words typed incorrectly." + "\n"
				+ "\n" + "* How will you be promoted from Medium Difficulty level to Hard Difficulty Level :-" + "\n"
				+ "You have to attempt to type atleast 30 words ,  with not more than 4 words typed incorrectly." + "\n"
				+ "\n"
				+ "* You will Finally be a CHAMPION if you attempt to type atleast 50 words , with not more than 8 words typed incorrectly."
				+ "\n" + "\n" + "EASY" + "\n"
				+ "1.) In this level, You will be Awarded with TWO points and an Increment of 2s in Time left." + "\n"
				+ "2.) For every word typed Incorrectly, time left will be decreased by 1s." + "\n" + "\n" + "MEDIUM"
				+ "\n" + "1.) In this level, You will be Awarded with THREE points and an Increment of 2s in Time left."
				+ "\n" + "2.) For every word typed Incorrectly, time left will be decreased by 1s." + "\n" + "\n"
				+ "HARD" + "\n"
				+ "1.) In this level, You will be Awarded with FIVE points and an Increment of 2s in Time left." + "\n"
				+ "2.) For every word typed Incorrectly, time left will be decreased by 1s." + "\n");

		Object msg[] = { "Select the difficulty level" };
		Object type[] = { "Hard", "Medium", "Easy" };
		int result = JOptionPane.showOptionDialog(this, msg, "Difficulty", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, type, null);

		if (type[result] == "Hard") {
			difficulty = difficultyStrArr[2];
			data = hardFeeder;
		}

		if (type[result] == "Medium") {
			difficulty = difficultyStrArr[1];
			data = medFeeder;
		}

		if (type[result] == "Easy") {
			difficulty = difficultyStrArr[0];
			data = easyFeeder;
		}

		// data = feeder.split(" ");
		initgame();

	}

	public void initgame() throws IOException {
		timeleft = 100;
		score = 0;
		running = false;
		timer = new Timer(4000, this);
		TotalWordsTypedCorrectly = 0;
		TotalWordsDisplayed = 0;
		// easyFeeder = feederBuilder("Easy");
		// medFeeder = feederBuilder("Medium");
		// hardFeeder = feederBuilder("Hard");
		lbltimer.setText("TIMER   : " + timeleft + "s");
		lblscore.setText("SCORE  :" + score);
		lblword.setText(" ");
		txtword.setText("");
		startbutton.setText("*START*");
		stopbutton.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startbutton) {
			handlestart();
		} else if (e.getSource() == stopbutton) {
			try {
				handlestop();
			} catch (IOException e1) {
				// e1.printStackTrace();
			}
		} else if (e.getSource() == timer) {
			try {
				handletimer();
			} catch (IOException e1) {
				// e1.printStackTrace();
			}
		}

	}

	public void handlestart() {
		if (running) {

			timer.stop();

			running = false;

			startbutton.setText("*RESUME*");

			txtword.setEditable(false);

		}

		else {

			timer.start();

			running = true;

			startbutton.setText("*PAUSE*");

			stopbutton.setEnabled(true);

			txtword.setEditable(true);

		}

	}

	public void handlestop() throws IOException {

		timer.stop();

		JOptionPane.showMessageDialog(this,
				"GAME OVER" + "\n" + " Your Score is : " + score + " \n THANKS FOR PLAYING");
		int choice = JOptionPane.showConfirmDialog(this, "Do You Want To Play Again?");

		if (choice == JOptionPane.YES_OPTION) {

			Object msg[] = { "Select the difficulty level" };
			Object type[] = { "Hard", "Medium", "Easy" };
			int result = JOptionPane.showOptionDialog(this, msg, "Difficulty", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, type, null);

			if (type[result] == "Hard") {
				difficulty = difficultyStrArr[2];
				data = hardFeeder;
			}

			if (type[result] == "Medium") {
				difficulty = difficultyStrArr[1];
				data = medFeeder;
			}

			if (type[result] == "Easy") {
				difficulty = difficultyStrArr[0];
				data = easyFeeder;
			}

			initgame();
		} else {
			super.dispose();
		}

	}

	public void handletimer() throws IOException {

		boolean easyTOmed = false;

		boolean medTOhard = false;

		boolean hardTOc = false;

		if (timeleft > 0) {

			timeleft--;

			String expectedword = txtword.getText();

			String actualword = lblword.getText();

			if (expectedword.equals(actualword) && expectedword.length() > 0) {

				//lblword.setText(data[(int) (Math.random() * data.length)]);

				score = score + scoreIncrement(difficulty);

				timeleft += 2;

				TotalWordsTypedCorrectly++;

			}

			// if (!expectedword.equals(actualword) && expectedword.length() >
			// 0) {
			//
			// timeleft -= 1;
			//
			// }

			if (difficulty == difficultyStrArr[0]) {
				if (TotalWordsDisplayed >= 12 && easyTOmed == false
						&& (TotalWordsDisplayed - TotalWordsTypedCorrectly) <= 2) {
					easyTOmed = true;

					JOptionPane.showMessageDialog(this, "Great! you've qualified for medium level");

					int choice2 = JOptionPane.showConfirmDialog(this,
							" Want to Increase the Difficulty level from Easy to Medium? ");

					if (choice2 == JOptionPane.YES_OPTION) {
						difficulty = difficultyStrArr[1];
						data = medFeeder;
					}

				}

			}

			if (difficulty == difficultyStrArr[1]) {
				if (TotalWordsDisplayed >= 30 && medTOhard == false
						&& (TotalWordsDisplayed - TotalWordsTypedCorrectly) <= 4) {
					medTOhard = true;

					JOptionPane.showMessageDialog(this, "Great! you've qualified for Hard level");

					int choice2 = JOptionPane.showConfirmDialog(this,
							" Want to Increase Difficulty level from Medium to Hard?");

					if (choice2 == JOptionPane.YES_OPTION) {
						difficulty = difficultyStrArr[2];
						data = hardFeeder;
					}

				}

			}

			if (difficulty == difficultyStrArr[2]) {

				if (TotalWordsDisplayed >= 50 && hardTOc == false
						&& (TotalWordsDisplayed - TotalWordsTypedCorrectly) <= 8) {
					hardTOc = true;

					JOptionPane.showMessageDialog(this, "Congratulations! you are a Champion Now!!!!");

				}

			}

			lblscore.setText("SCORE :" + score);

			TotalWordsDisplayed++;
			lbltimer.setText("TIMER  :" + timeleft + "s");

			lblword.setText(data[(int) (Math.random() * data.length)]);

			txtword.setText("");
			txtword.setFocusable(true);
		}

		else {
			handlestop();
		}
	}

	private String feederBuilder(String difficulty) throws IOException {

		String fileName = null;

		if (difficulty == "Easy") {
			fileName = "EasyFeeder.txt";
		}

		if (difficulty == "Hard") {
			fileName = "HardFeeder.txt";
		}

		if (difficulty == "Medium") {
			fileName = "MediumFeeder.txt";
		}

		List<String> dataReadFromFile = readTextFileByLines(INPUT_FOLDER_BASE_ADDRESS + fileName);

		String str = "";

		for (int i = 0; i < dataReadFromFile.size(); i++) {

			str = str + " " + dataReadFromFile.get(i);

		}

		return str;

	}

	private List<String> readTextFileByLines(String fileName) throws IOException {
		return Files.readAllLines(Paths.get(fileName));
	}

	private int scoreIncrement(String difficulty) {
		int retVal = 0;

		if (difficulty == "Easy") {
			retVal = 2;
		}

		if (difficulty == "Hard") {
			retVal = 5;
		}

		if (difficulty == "Medium") {
			retVal = 3;
		}

		return retVal;

	}

}
