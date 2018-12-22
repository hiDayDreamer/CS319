import java.util.*;

public class GameSolver {

    private TreeNode initialConfig;
    private Set<String> hashTable;
    private LinkedList<TreeNode> nodes;
    private boolean gameFinished;
	private boolean hintWanted;

	class TreeNode{
	    private Map currentConfiguration;
	    TreeNode parent;

	    public TreeNode(Map config,TreeNode whoAmI){
	        currentConfiguration = config.clone();
	        parent = whoAmI;
	    }
	    public Map  getMap(){
	        return currentConfiguration;
	    }
	    public TreeNode getParent(){
	        return parent;
	    }
	}

	public GameSolver(Map toSolve) {
		initialConfig = new TreeNode(toSolve,null);
		hashTable = new HashSet<String>();
		gameFinished = false;
		nodes = new LinkedList<TreeNode>();
	}

	public boolean getHintWanted() {
		return this.hintWanted;
	}

	/**
	 *
	 * @param hintWanted
	 */
	public void setHintWanted(boolean hintWanted) {
		this.hintWanted = hintWanted;
	}

	/**
	 *
	 * @param hintWanted
	 */
	public LinkedList<int[]> generateHint(boolean hintWanted) {
		return bft(initialConfig);
	}


    public LinkedList<int[]> bft(TreeNode curr) {
        nodes.add( curr);
        TreeNode temp;
        while ( nodes.size() > 0 ) {
            temp = nodes.pollFirst();
            if ( gameFinished(temp) ) {
                System.out.println("Finished");
                return solutionPath(temp);
            }
            if ( isSeenBefore(temp) ) {
                continue;
            }
            hashTable.add(hashMap(temp.getMap()));
            Car[] cars = temp.getMap().getCars();
            for (int i = 0; i<cars.length; i++){
                Map newConfig = canMove(temp.getMap(),i,1);
                if (newConfig!=null){
                    TreeNode tmp = new TreeNode(newConfig, temp);
                    // System.out.println("My Parent: \n" + temp.getMap().toString());
                    // System.out.println( "Postive movement\n " + newConfig.toString());
                    nodes.add(tmp);
                    //dft(tmp);
                    // if (gameFinished)
                    //     return;
                }else {
                //System.out.println("Positive Movement Is null here \n"  +i);
                }
                newConfig = canMove(temp.getMap(),i,-1);
                if (newConfig!=null){
                    TreeNode tmp = new TreeNode(newConfig, temp);
                    // System.out.println("My Parent: \n" + temp.getMap().toString());
                    // System.out.println("Negative Movement\n "  +newConfig.toString());
                    nodes.add(tmp);
                    //(tmp);
                    // if (gameFinished)
                    //     return;
                }else {
                    //System.out.println("Negative Movement Is null here \n"  +i);
                }
            }
        }
		return null;
    }

    private String hashMap(Map n){
        String tmp = "";
        for (int i = 0; i < n.getCars().length; i++){
            tmp = tmp + i +","+ n.getCars()[i].getX() +","+ n.getCars()[i].getHorizontalX() +","+ n.getCars()[i].getY() +","+ n.getCars()[i].getVerticalY()+",";
        }
        //System.out.println(tmp);
        return tmp;
    }

    private boolean isSeenBefore(TreeNode test){
        String tmp = hashMap(test.getMap());
        return hashTable.contains(tmp);
    }
    private boolean gameFinished(TreeNode possibleSolution){
        return possibleSolution.getMap().getPlayer().getY() == 4;
    }

    private Map canMove(Map config,int index,int unit){
        Map cloned = config.clone();
        Block[][] blocks = cloned.getBlocks();
        Car movable = cloned.getCars()[index];

        if (movable.getCarDirection() == 0 || movable.getCarDirection() == 2){
            int y = movable.getY() + unit;
            int endy = movable.getVerticalY() + unit;
            if (unit == 1){
                if (endy > 5){
                    return null;
                } else {
                    if (blocks[movable.getX()][endy].isOccupied()){
                        return null;
                    } else {
                        blocks[movable.getX()][endy].setOccupied(true);
                        blocks[movable.getX()][y-unit].setOccupied(false);
                        movable.setVerticalY(y,endy);
                        return cloned;
                    }
                }
            } else {
                if (y < 0){
                    return null;
                } else {
                    if (blocks[movable.getX()][y].isOccupied()){
                        //System.out.println("Block " +movable.getX() + " " + y);
                        return null;
                    } else {
                        blocks[movable.getX()][y].setOccupied(true);
                        blocks[movable.getX()][endy-unit].setOccupied(false);
                        movable.setVerticalY(y,endy);
                        return cloned;
                    }
                }
            }
        } else {
            int x = movable.getX() + unit;
            int endx = movable.getHorizontalX() + unit;
            if (unit == 1){
                if (endx > 5){
                    return null;
                } else {
                    if (blocks[endx][movable.getY()].isOccupied()){
                        return null;
                    } else {
                        blocks[endx][movable.getY()].setOccupied(true);
                        blocks[x-unit][movable.getY()].setOccupied(false);
                        movable.setHorizontalX(x,endx);
                        return cloned;
                    }
                }
            } else {
                if (x < 0){
                    return null;
                } else {
                    if (blocks[x][movable.getY()].isOccupied()){
                        return null;
                    } else {
                        blocks[x][movable.getY()].setOccupied(true);
                        blocks[endx-unit][movable.getY()].setOccupied(false);
                        movable.setHorizontalX(x,endx);
                        return cloned;
                    }
                }
            }

        }
    }

    private LinkedList<int[]> solutionPath(TreeNode last){
        TreeNode cur = last;
        TreeNode parent = cur.parent;
		LinkedList<int[]> moves = new LinkedList();
        //String tmp = "";
        while(parent!= null){
            //tmp = findMove(cur) + tmp;
			moves.addFirst(findMove(cur) );
            cur = parent;
            parent = parent.parent;
        }
		return moves;
        //System.out.println(tmp);
    }

    private int[] findMove(TreeNode current){
		int[] move = new int[7];
        TreeNode parent = current.parent;
        if (parent!=null){
            Car test, test2;
            for (int i = 0; i < current.getMap().getCars().length; i++){
                test = current.getMap().getCars()[i];
                test2 = parent.getMap().getCars()[i];
                if (test.getX() != test2.getX() || test.getHorizontalX() !=  test2.getHorizontalX()
                    || test.getY() != test2.getY() || test.getVerticalY() != test2.getVerticalY()){
					move[0] = i;
					move[1] = test.getCarDirection();
					move[2] = test.getLength();
					move[3] = test2.getX();
					move[4] = test2.getY();
					move[5] = test.getX();
					move[6] = test.getY();
					return move;
                    //return "Move " + i + " from location: " + test2.getX() + ", " + test2.getY() + " to: "+ test.getX() + ", " + test.getY() +"\n";
                }
            }
        }
        return null;
    }

	// public class Graph {
	//
	// 	private int vertexNumber;
	// 	private LinkedList algoList;
	//
	// 	public int getVertexNumber() {
	// 		return this.vertexNumber;
	// 	}
	//
	// 	/**
	// 	 *
	// 	 * @param vertexNumber
	// 	 */
	// 	public void setVertexNumber(int vertexNumber) {
	// 		this.vertexNumber = vertexNumber;
	// 	}
	//
	// 	public LinkedList getAlgoList() {
	// 		return this.algoList;
	// 	}
	//
	// 	/**
	// 	 *
	// 	 * @param algoList
	// 	 */
	// 	public void setAlgoList(LinkedList algoList) {
	// 		this.algoList = algoList;
	// 	}
	//
	// }

}
