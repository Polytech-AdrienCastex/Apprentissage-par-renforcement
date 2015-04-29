package simu;

import javax.swing.SwingUtilities;

import environnement.Environnement;
import environnement.crawler.CrawlingRobotEnvironnement;
import agent.rlagent.QLearningAgent;
import agent.rlagent.RLAgent;
import vueCrawler.VueCrawlerAbstrait;
import vueCrawler.VueCrawlerManuel;
import vueCrawler.VueCrawlerRL;

public class testQLAgentCrawler {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			
			double gamma=0.9;
			double alpha=0.1;
			int nbEtatBras=4;
			int nbEtatMain=6;
			
			CrawlingRobotEnvironnement g = new CrawlingRobotEnvironnement(nbEtatBras,nbEtatMain);
		//	System.out.println(gmdp.getEtats().size());
			
			//RLAgent a = new QLearningAgent(alpha,gamma,g,nbEtatBras*nbEtatMain,4);
			RLAgent a = new QLearningAgent(alpha,gamma,g);
			a.setStratExplorationGreedy(0.1);//sinon strat exploration par defaut est manuelle
			
			a.setMaxnbpasparepisode(100);
			VueCrawlerAbstrait vue = new VueCrawlerRL(g,a);
			vue.setVisible(true);
			
			
		}
	});
	}
}
