import java.util.LinkedList;

public class GameSolverold {

	private boolean hintWanted;

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
	public int generateHint(boolean hintWanted) {
		// TODO - implement GameSolver.generateHint
		throw new UnsupportedOperationException();
	}


	public class Graph {

		private int vertexNumber;
		private LinkedList algoList;

		public int getVertexNumber() {
			return this.vertexNumber;
		}

		/**
		 * 
		 * @param vertexNumber
		 */
		public void setVertexNumber(int vertexNumber) {
			this.vertexNumber = vertexNumber;
		}

		public LinkedList getAlgoList() {
			return this.algoList;
		}

		/**
		 * 
		 * @param algoList
		 */
		public void setAlgoList(LinkedList algoList) {
			this.algoList = algoList;
		}

	}

}