import java.io.File;

public class Tutorial {
	private String tutorialLoc;
	private String tutorialType;

	public Tutorial(String tutorialLoc, String tutorialType) {
		this.tutorialLoc = tutorialLoc;
		this.tutorialType = tutorialType;
	}

	public String getTutorialLoc() {
		return tutorialLoc;
	}

	public void setTutorialLoc(String tutorialLoc) {
		this.tutorialLoc = tutorialLoc;
	}

	public String getTutorialType() {
		return tutorialType;
	}

	public void setTutorialType(String tutorialType) {
		this.tutorialType = tutorialType;
	}

}
