package simu;



import environnement.Action;
import environnement.Etat;
import environnement.crawler.CrawlingRobot;
import environnement.crawler.CrawlingRobotEnvironnement;
import environnement.gridworld.GridworldEnvironnement;
import environnement.gridworld.GridworldMDP;
import javafx.util.Pair;
import javax.swing.SwingUtilities;
import vueCrawler.VueCrawlerAbstrait;
import vueCrawler.VueCrawlerManuel;
import vueGridworld.VueGridworldManuel;

public class testMoveCrawler {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// cree une nouvelle tache, une instance de Runnable, qui est placee a la fin de la file de l'EDT par invokeLater(). 
    SwingUtilities.invokeLater(new Runnable(){
		public void run(){

			int nbEtatBras=9;
			int nbEtatMain=13;
			
			CrawlingRobotEnvironnement g = new CrawlingRobotEnvironnement(nbEtatBras,nbEtatMain);
			CrawlingRobot.DISP=true;
			VueCrawlerAbstrait vue = new VueCrawlerManuel(g);
			vue.setVisible(true);
			
			
			
		}
	});
	}
}
