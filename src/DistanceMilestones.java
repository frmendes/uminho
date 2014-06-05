
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tiago
 */
public class DistanceMilestones extends Milestones{
    private HashMap<Integer,Long> distanceMS;
    
    //constructors public 
    public DistanceMilestones(){
        super();
        this.distanceMS = new HashMap<Integer,Long>();
        this.populateMilestones();
    }
    
    public DistanceMilestones(HashMap<Long,Integer> cms,HashMap<Integer,Long> dms){
        super(cms);
        this.distanceMS = cloneDistanceMilestones(dms);
    }
    
    public DistanceMilestones(DistanceMilestones dms){
        super(dms);
        this.distanceMS = dms.getDistanceMilestones();
    }
    
    //setters & getters
    public HashMap<Integer,Long> getDistanceMilestones()
    {return cloneDistanceMilestones(this.distanceMS);}
    
    public void setDistanceMS(HashMap<Integer,Long> distanceMS)
    {this.distanceMS = cloneDistanceMilestones(distanceMS);}
    
    //methods
    public HashMap<Integer,Long> cloneDistanceMilestones(HashMap<Integer,Long> dm) {
        HashMap<Integer,Long> aux = new HashMap<Integer,Long>();
        for(Map.Entry<Integer,Long> dms: dm.entrySet())
            aux.put(dms.getKey(), dms.getValue());
        return aux;
    }
    
    public void populateMilestones(){
        super.populateMilestones();
        this.distanceMS.put(1000,-1L);
        this.distanceMS.put(5000,-1L);
        this.distanceMS.put(10000,-1L);
        this.distanceMS.put(20000,-1L);
    }
    
    public void addData(DistanceActivity act){
        super.addData(act);
        long actDuration = act.getDuration();
        int actDistance = act.getDistance();
        
        for(Map.Entry<Integer,Long> pair : distanceMS.entrySet()){
            if(actDistance >= pair.getKey()){
                long aux = ruleOfThree(actDuration/60000L, (long) actDistance, pair.getKey());
                
                if(aux > pair.getValue())
                    distanceMS.put( (int)pair.getKey(), aux);
            }
            else break;
        }
    }
    //essentials
    public DistanceMilestones clone()
    {return new DistanceMilestones(this);}
    
    public String toString(){
        StringBuilder result = new StringBuilder();
        
        for(Map.Entry<Integer,Long> pair: this.distanceMS.entrySet()){
            result.append( (pair.getKey())/1000 );
            result.append( " km: ");
            result.append( StatEntry.formatMillis( pair.getValue() ));
        }
        return ( super.toString() + result.toString() );
    }
    
    public boolean equals(Object o){
        if (this == o) return true;
        
        if (o == null || this.getClass() != o.getClass()) return false;
        
        DistanceMilestones dms = (DistanceMilestones) o;
        
        return this.distanceMS.equals(dms.getDistanceMilestones());
    }
}
