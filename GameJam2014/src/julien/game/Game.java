package julien.game;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import jitou.batiments.Batiment;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Ennemi;
import julien.map.Case;
import julien.mechant.EnergieMal;
import julien.mechant.Mechant;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.util.ResourceLoader;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

public class Game  extends BasicGame{

	private static Game instance;
	private final int defilement = -10;
	private int jaugesMaxH = 200;
	private int jaugesW = 20;
	private int facteurJauge = 1;
	private int espaceJauge = 9;
	private int baseJauge = 500;

	private Sprite[] liste;
	private int points=0;

	private LWJGLRenderer lwjglRenderer;
	private ThemeManager theme;
	private GUI gui;
	private Widget root;
	private TWLInputAdapter twlInputAdapter;
	private BoardGame board;
	private GameContainer container;

	private int offsetX, offsetY;
	private int containerW, containerH;
	private int pressedKey;
	Input input;

	private ArrayList<Case> casesADessiner = new ArrayList<Case>();
	private ArrayList<Case> casesHorsEcran = new ArrayList<Case>();
	private static ArrayList<Citoyen> citoyenSansSprite = new ArrayList<Citoyen>();
	private ArrayList<GCarte> gCartes = new ArrayList<GCarte>();
	private ArrayList<GEnnemi> gEnnemis = new ArrayList<GEnnemi>();
	private ArrayList<SpriteHumain> spritesHumains = new ArrayList<SpriteHumain>();
	private ArrayList<FX> fx = new ArrayList<FX>();

	private PresentationDebut presentation = null;
	private ToolTips toolTip = null;
	FX maladie;
	int kit=0;
	private Image[] decombres;
	public Game(String titre) {
		super(titre);
	}

	public void init(GameContainer container) throws SlickException {
		input = container.getInput();
		this.container = container;
		board = BoardGame.boardGame;
		containerH = container.getHeight();
		containerW = container.getWidth();

		offsetX = (int) (board.getDimensionworldx() *Case.getDimensionX()/2.-3*Case.getDimensionX());
		offsetY = (int) (board.getDimensionworldy() *Case.getDimensionY()/2.+Case.getDimensionY());

		instance = this;
		Mechant.getInstance().premierePioche();

		int h = board.getDimensionworldy() ;
		int w = board.getDimensionworldx() ;

		for (int x = 0 ; x < w ; x++) {
			for (int y = 0 ; y < h ; y++) {
				casesHorsEcran.add(new Case(x*Case.getDimensionX()-offsetX,y*Case.getDimensionY()-offsetY,x,y));
			}
		}
		computeCaseADessiner();
		maladie = new FX(FXtype.maladie,0,0);
		maladie.initAnimation();
		maladie.getAnim().stopAt(-1);
		decombres = new Image[2];
		decombres[0] = new Image("julien/images/debris01.png");
		decombres[1] = new Image("julien/images/debris02.png");
		toolTip = new ToolTips(container);
		presentation = new PresentationDebut(container);


	}


	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		//System.out.println("Id : "+key+" , char : "+c);

		pressedKey = key;


	}
	public void keyReleased(int key, char c) {
		if (key == pressedKey) {
			pressedKey = 0;
		}
	}


	public void checkKey() {
		if (input.isKeyDown(Input.KEY_UP))
		{
			offsetY = offsetY - defilement;
			computeCaseADessiner();
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			offsetY = offsetY + defilement;
			computeCaseADessiner();
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			offsetX = offsetX + defilement;
			computeCaseADessiner();
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			offsetX = offsetX - defilement;
			computeCaseADessiner();
		}

	}



	public void mouseClicked(int button,int  x, int  y, int  clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		System.out.println("Debut clic");
		for(int i=0;i<this.gCartes.size();i++){
			String str =gCartes.get(i).collisionCarte(x, y);
			if(!str.isEmpty()){
				toolTip.set(str);

			} 
		}
		System.out.println("Fin clic");
	}





	public void render(GameContainer container, Graphics g) throws SlickException {
		checkKey();
		if(casesADessiner.size()>0){
			kit=(kit+1)%casesADessiner.size();

			casesADessiner.get(kit).redraw();
		}

		g.drawString("Population : "+BoardGame.boardGame.getCitoyens().size(), 50, 50);

		for(int i=0;i<this.gCartes.size();i++){
			gCartes.get(i).survolDecalage(input.getMouseX(),input.getMouseY());
		}


		for (Citoyen c : citoyenSansSprite) {
			this.spritesHumains.add(new SpriteHumain(c));
		}
		citoyenSansSprite.clear();
		DecimalFormat df = new DecimalFormat("00.0");

		for (int i = 0 ; i < casesADessiner.size() ; i++) {
			g.drawImage(casesADessiner.get(i).getImage(), casesADessiner.get(i).getX(), casesADessiner.get(i).getY());	
			if(casesADessiner.get(i).batiment!=null){
				g.setColor(Color.black);
				g.drawString(casesADessiner.get(i).batiment.info(),casesADessiner.get(i).getX(), casesADessiner.get(i).getY());
				double temp = casesADessiner.get(i).batiment.getTemperatureSalle();
				g.setColor(new Color(  (float)Math.min(Math.max(0,(temp*2)/120.0),1)  ,0.0f,    (float)Math.min(Math.max(0,((90-(temp*2))/70.0)),1)));
				g.drawString(df.format(temp)+"°C",casesADessiner.get(i).getX(), 
						casesADessiner.get(i).getY()+julien.map.Case.getDimensionY()-27);
				//	System.out.println(casesADessiner.get(i).batiment.getTemperatureSalle());
				if (casesADessiner.get(i).batiment.estEndommage()) {
					if (casesADessiner.get(i).typeDeSol <= 1) {
						g.drawImage(decombres[0], casesADessiner.get(i).getX(), casesADessiner.get(i).getY());
					}
					else {
						g.drawImage(decombres[1], casesADessiner.get(i).getX(), casesADessiner.get(i).getY());
					}
				}
			}


		}

		/*
		for(int k=0;k<jitou.global.CitoyenDehors.liste_cit.size();k++){
			g.drawRect((float) jitou.global.CitoyenDehors.liste_cit.get(k).getX()*Case.getDimensionX(),  
					-30+0*Case.getDimensionY()-offsetY , 10, 10);
		}
		 */


		ArrayList<Citoyen> citoyens = board.getCitoyens();
		for (int i = 0 ; i < spritesHumains.size() ; i++) {
			spritesHumains.get(i).changeState(container,this);
			if (spritesHumains.get(i).actualiser(this)) i--;

		}

		for(SpriteHumain cit : spritesHumains){

			g.drawAnimation(cit.getAnim(), cit.x, cit.y);
			if (cit.getC().estMalade()) {
				g.drawAnimation(maladie.getAnim(), cit.x, cit.y);
			}

			//	g.drawImage(new SpriteHumain(null).getImage(),(float)((cit.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + cit.getPosInside().getX()*(Case.getDimensionX()/2))-offsetX),
			//			(float)(((cit.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + cit.getPosInside().getY()*(Case.getDimensionY()/2)))-offsetY - containerH) * -1		
			//	);
			//g.setColor(Color.red);
			//g.fillOval((float)((cit.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + cit.getPosInside().getX()*(Case.getDimensionX()/2)))-offsetX,
			//		(float)(((cit.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + cit.getPosInside().getY()*(Case.getDimensionY()/2)))-offsetY - containerH) * -1, (float)10,(float) 10);		
		}

		//g.drawString("Score : "+points, 100,50);
		for (GEnnemi e : gEnnemis) {
			e.changeState(container, this);
			g.drawAnimation(e.getAnim(), e.getX()-e.w/2, e.getY()-e.h/2);	
			//System.out.println(			e.getAnim().isStopped());
		}

		ArrayList<FX> fxToRemove = new ArrayList<FX>();
		for (FX f : fx) {
			g.drawAnimation(f.getAnim(), f.getX(), f.getY());
			if (f.getAnim().isStopped()) {
				fxToRemove.add(f);
			}
		}
		for (FX f : fxToRemove) {
			fx.remove(f);
		}
		for (GCarte carte : gCartes) {
			g.drawImage(carte.getImage(), carte.getX(), carte.getY());	
			g.drawImage(carte.getImageEffetMal(), carte.getX()+10, carte.getY()+20);	
			g.drawImage(carte.getImageEffetBonne(), carte.getX()+10, carte.getY()+carte.getDimensionY()/2+20);	

		}


		drawJauges(container , g);
		toolTip.draw(container);
		presentation.draw(container);

	}

	private void drawJauges(GameContainer container2, Graphics g) {
		EnergieMal[] ener = Mechant.getInstance().getEnergies();
		g.setColor(Color.cyan);
		g.drawString(String.valueOf(ener[0].getTotal()), espaceJauge*2,             baseJauge + 4);		
		g.fillRect(espaceJauge*2, baseJauge, this.jaugesW, -1 * Math.min(jaugesMaxH, ener[0].getTotal()*facteurJauge));
		g.setColor(Color.blue);
		g.drawString(String.valueOf(ener[1].getTotal()), espaceJauge*3 + jaugesW,   baseJauge + 4);
		g.fillRect(espaceJauge*3 + jaugesW, baseJauge, this.jaugesW, -1 * Math.min(jaugesMaxH, ener[1].getTotal()*facteurJauge));
		g.setColor(Color.red);
		g.fillRect(espaceJauge*4 + 2*jaugesW, baseJauge, this.jaugesW,-1 * Math.min(jaugesMaxH, ener[2].getTotal()*facteurJauge));
		g.drawString(String.valueOf(ener[2].getTotal()), espaceJauge*4 + 2*jaugesW, baseJauge + 4);
	}

	public void changeCitoyen(Citoyen c) {
		//	for (int i = 0 ; i < spritesHumains.size() ; i++)
	}

	public void redefinirLesCartes() {
		gCartes.clear();
		for (int i = 0 ; i < Mechant.getInstance().getCartes().size(); i++) {
			gCartes.add(new GCarte(Mechant.getInstance().getCartes().get(i),i,container));
		}
	}


	public static void addSpriteCitoyen(Citoyen c) {
		Game.citoyenSansSprite.add(c);
		//Game.spritesHumains.add(new SpriteHumain(c));
	}


	public void update(GameContainer container, int delta) throws SlickException {
		board.update(delta);
		toolTip.update(delta);
		presentation.update(delta);
		containerH = container.getHeight();
		containerW = container.getWidth();


	}

	private void computeCaseADessiner() {
		int h = board.getDimensionworldy() ;
		int w = board.getDimensionworldx() ;

		for (int i = 0 ; i < casesHorsEcran.size(); i++) {
			if (batimentIsOnScreen(casesHorsEcran.get(i).getXcor(),casesHorsEcran.get(i).getYcor())) {
				casesADessiner.add(casesHorsEcran.get(i));
				casesHorsEcran.remove(i);
				i--;
				//	System.out.println("cases �� pas dessiner : " + casesHorsEcran.size());

			}
		}

		for (int i = 0 ; i < casesADessiner.size(); i++) {
			if (!batimentIsOnScreen(casesADessiner.get(i).getXcor(),casesADessiner.get(i).getYcor())) {
				casesHorsEcran.add(casesADessiner.get(i));
				casesADessiner.remove(i);
				i--;
				//System.out.println("cases �� dessiner : " + casesADessiner.size());
			}
			else {
				casesADessiner.get(i).calculerPosition(offsetX,offsetY,containerH);
			}
		}
		//	System.out.println("cases �� dessiner : " + casesADessiner.size());

		/*
		for (int x = 0 ; x < w ; x++) {
			for (int y = 0 ; y < h ; y++) {
				if (batimentIsOnScreen(x,y)) {
					casesADessiner.add(new Case(x*Case.getDimensionX()-offsetX,y*Case.getDimensionY()-offsetY,x,y));
				}
			}
		}*/
	}

	private boolean batimentIsOnScreen(int x, int y) {
		int xmin = x*Case.getDimensionX() - offsetX;
		int ymin = y*Case.getDimensionY() - offsetY;

		if (xmin + Case.getDimensionX() >= 0 &&
				ymin + Case.getDimensionY() >= 0 &&
				xmin < containerW &&
				ymin < containerH
				) {
			return true;
		}

		return false;

	}

	public void changementCase(int x, int y) {
		//TODO : 


		for (Case c : casesADessiner) {
			c.redraw();
		}
		for (Case c : casesHorsEcran) {
			c.redraw();
		}
	}

	public static Game getInstance() {
		return instance;
	}

	public Sprite[] getListe() {
		return liste;
	}

	public void setListe(Sprite[] liste) {
		this.liste = liste;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public LWJGLRenderer getLwjglRenderer() {
		return lwjglRenderer;
	}

	public void setLwjglRenderer(LWJGLRenderer lwjglRenderer) {
		this.lwjglRenderer = lwjglRenderer;
	}

	public ThemeManager getTheme() {
		return theme;
	}

	public void setTheme(ThemeManager theme) {
		this.theme = theme;
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public Widget getRoot() {
		return root;
	}

	public void setRoot(Widget root) {
		this.root = root;
	}

	public TWLInputAdapter getTwlInputAdapter() {
		return twlInputAdapter;
	}

	public void setTwlInputAdapter(TWLInputAdapter twlInputAdapter) {
		this.twlInputAdapter = twlInputAdapter;
	}

	public BoardGame getBoard() {
		return board;
	}

	public void setBoard(BoardGame board) {
		this.board = board;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public int getContainerW() {
		return containerW;
	}

	public void setContainerW(int containerW) {
		this.containerW = containerW;
	}

	public int getContainerH() {
		return containerH;
	}

	public void setContainerH(int containerH) {
		this.containerH = containerH;
	}

	public int getPressedKey() {
		return pressedKey;
	}

	public void setPressedKey(int pressedKey) {
		this.pressedKey = pressedKey;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public ArrayList<Case> getCasesADessiner() {
		return casesADessiner;
	}

	public void setCasesADessiner(ArrayList<Case> casesADessiner) {
		this.casesADessiner = casesADessiner;
	}

	public ArrayList<Case> getCasesHorsEcran() {
		return casesHorsEcran;
	}

	public void setCasesHorsEcran(ArrayList<Case> casesHorsEcran) {
		this.casesHorsEcran = casesHorsEcran;
	}

	public static ArrayList<Citoyen> getCitoyenSansSprite() {
		return citoyenSansSprite;
	}

	public static void setCitoyenSansSprite(ArrayList<Citoyen> citoyenSansSprite) {
		Game.citoyenSansSprite = citoyenSansSprite;
	}

	public ArrayList<SpriteHumain> getSpritesHumains() {
		return spritesHumains;
	}

	public void setSpritesHumains(ArrayList<SpriteHumain> spritesHumains) {
		this.spritesHumains = spritesHumains;
	}

	public int getDefilement() {
		return defilement;
	}

	public static void setInstance(Game instance) {
		Game.instance = instance;
	}

	public void ennemiCreve(Ennemi e) {

		for(int i=0;i<gEnnemis.size();i++){
			if(gEnnemis.get(i).getEnemis()==e) 		gEnnemis.remove(gEnnemis.get(i));

		}

	}

	public void nouvelEnnemi(Ennemi e) {
		this.gEnnemis.add(new GEnnemi(e));

	}

	public static void addFX(Citoyen c, FXtype type) {
		int x = (int) ((c.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + c.getPosInside().getX()*(Case.getDimensionX()/2))-Game.getInstance().getOffsetX());
		int y = (int)(((c.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + c.getPosInside().getY()*(Case.getDimensionY()/2)))-Game.getInstance().getOffsetY() - Game.getInstance().container.getHeight()) * -1;

		Game.addFX(x , y , type);
	}

	public static void addFX(Batiment b, FXtype type) {
		int x = (int) (b.getPos().x*Case.getDimensionX()-Game.getInstance().offsetX);
		int y = (int)((b.getPos().y*Case.getDimensionY()-Game.getInstance().offsetY - Game.getInstance().containerH) * -1 - Case.getDimensionY());
		Game.addFX(x , y , type);
	}

	public static void addFX(int x , int y, FXtype type) {
		Game.getInstance().fx.add(new FX(type , x , y));
	}

}
