import java.util.*;

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
    /*public void add(TreeNode addition){
        children.add(addition);
    }*/
}
