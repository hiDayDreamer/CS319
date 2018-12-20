import java.util.*;

class GameSolverTester{
    TreeNode initialConfig;
    Set<String> hashTable;
    LinkedList<TreeNode> nodes;
    private boolean gameFinished;

    public GameSolverTester(Map toSolve){
        initialConfig = new TreeNode(toSolve,null);
        hashTable = new HashSet<String>();
        gameFinished = false;
        nodes = new LinkedList<TreeNode>();
    }
    public void getHintMovesList(){
      bft(initialConfig);
    }

    public void bft(TreeNode curr) {
        nodes.add( curr);
        TreeNode temp;
        while ( nodes.size() > 0 ) {
            temp = nodes.pollFirst();
            if ( gameFinished(temp) ) {
                System.out.println("Finished");
                return;
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
                    System.out.println("My Parent: \n" + temp.getMap().toString());
                    System.out.println( "Postive movement\n " + newConfig.toString());
                    nodes.add(tmp);
                    //dft(tmp);
                    // if (gameFinished)
                    //     return;
                }else {
                System.out.println("Positive Movement Is null here \n"  +i);
                }
                newConfig = canMove(temp.getMap(),i,-1);
                if (newConfig!=null){
                    TreeNode tmp = new TreeNode(newConfig, temp);
                    System.out.println("My Parent: \n" + temp.getMap().toString());
                    System.out.println("Negative Movement\n "  +newConfig.toString());
                    nodes.add(tmp);
                    //(tmp);
                    // if (gameFinished)
                    //     return;
                }else {
                    System.out.println("Negative Movement Is null here \n"  +i);
                }
            }
        }
    }

    public void dft(TreeNode current){
        if (gameFinished)
            return;
        if (isSeenBefore(current)){
            System.out.println("We have Seen this map before ");
            return;
        }
        hashTable.add(hashMap(current.getMap()));
        if (gameFinished(current)){
            gameFinished =true;
            System.out.println("Wise me found the solution");
            return;
        }
        Car[] cars = current.getMap().getCars();
        for (int i = 0; i<cars.length; i++){
            Map newConfig = canMove(current.getMap(),i,1);
            if (newConfig!=null){
                TreeNode tmp = new TreeNode(newConfig, current);
                System.out.println("My Parent: \n" + current.getMap().toString());
                System.out.println( "Postive movement\n " + newConfig.toString());
                dft(tmp);
                if (gameFinished)
                    return;
            }else {
            System.out.println("Positive Movement Is null here \n"  +i);
            }
            newConfig = canMove(current.getMap(),i,-1);
            if (newConfig!=null){
                TreeNode tmp = new TreeNode(newConfig, current);
                System.out.println("My Parent: \n" + current.getMap().toString());
                System.out.println("Negative Movement\n "  +newConfig.toString());
                dft(tmp);
                if (gameFinished)
                    return;
            }else {
                System.out.println("Negative Movement Is null here \n"  +i);
            }
        }
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
                        System.out.println("Block " +movable.getX() + " " + y);
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

    public static void main(String[] args){
        Map testMap = new Map(6);
        GameSolverTester testSolver = new GameSolverTester(testMap);
        testSolver.getHintMovesList();
    }
}
