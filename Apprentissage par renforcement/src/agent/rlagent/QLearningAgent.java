package agent.rlagent;

import environnement.Action;
import environnement.Environnement;
import environnement.Etat;
import environnement.gridworld.ActionGridworld;
import environnement.gridworld.EtatGrille;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
/**
 * 
 * @author laetitiamatignon
 *
 */
public class QLearningAgent extends RLAgent
{
    /**
     * 
     * @param alpha
     * @param gamma
     * @param Environnement
     */
    public QLearningAgent(double alpha, double gamma, Environnement _env)
    {
        super(alpha, gamma,_env);
        
        
        q = new HashMap<>();
    }
    
    protected Map<Pair<Etat, Action>, Double> q;




    /**
     * renvoi la (les) action(s) de plus forte(s) valeur(s) dans l'etat e
     *  
     *  renvoi liste vide si aucunes actions possibles dans l'etat 
     */
    @Override
    public List<Action> getPolitique(Etat e)
    {
        if(e.estTerminal())
            return getActions(e);
        
        List<Action> actions = new ArrayList<>();
        double max = Double.MIN_VALUE;
        
        for(Action a : getActions(e))
        {
            double v = getQValeur(e, a);
            if(v > max)
            {
                max = v;
                actions.clear();
            }
            
            if(v == max)
                actions.add(a);
        }
        
        return actions;
    }
    
    protected List<Action> getActions(Etat e)
    {
        return getEnv().getActionsPossibles(e);
    }

    /**
     * @return la valeur d'un etat
     */
    @Override
    public double getValeur(Etat e)
    {
        return getActions(e)
                .stream()
                .mapToDouble(a -> getQValeur(e, a))
                .max()
                .orElse(0.0);
    }

    /**
     * 
     * @param e
     * @param a
     * @return Q valeur du couple (e,a)
     */
    @Override
    public double getQValeur(Etat e, Action a)
    {
        Pair<Etat, Action> p = new Pair<>(e, a);
        
        return q.getOrDefault(p, 0.0);
    }

    /**
     * setter sur Q-valeur
     */
    @Override
    public void setQValeur(Etat e, Action a, double d)
    {
        Pair<Etat, Action> p = new Pair<>(e, a);
        
        q.put(p, d);

        this.notifyObs();
    }


    /**
     *
     * mise a jour de la Q-valeur du couple (e,a) apres chaque interaction <etat e,action a, etatsuivant esuivant, recompense reward>
     * la mise a jour s'effectue lorsque l'agent est notifie par l'environnement apres avoir realise une action.
     * @param e
     * @param a
     * @param esuivant
     * @param reward
     */
    @Override
    public void endStep(Etat e, Action a, Etat esuivant, double reward)
    {
        double value = (1 - getAlpha()) * getQValeur(e, a) + getAlpha() * (reward + getGamma() * getValeur(esuivant));
        setQValeur(e, a, value);
    }

    @Override
    public Action getAction(Etat e)
    {
        this.actionChoisie = this.stratExplorationCourante.getAction(e);
        return this.actionChoisie;
    }

    /**
     * reinitialise les Q valeurs
     */
    @Override
    public void reset()
    {
        this.episodeNb = 0;
        
        q.clear();
    }
}
