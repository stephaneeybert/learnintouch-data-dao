package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningCourse implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String image;
    private boolean instantCorrection;
    private boolean instantCongratulation;
    private boolean instantSolution;
	private boolean importable;
	private boolean secured;
	private int freeSamples;
    private boolean autoSubscription;
	private boolean autoUnsubscription;
	private boolean interruptTimedOutExercise;
	private boolean resetExerciseAnswers;
	private boolean exerciseOnlyOnce;
	private boolean exerciseAnyOrder;
	private boolean saveResultOption;
	private boolean shuffleQuestions;
	private boolean shuffleAnswers;
	private ElearningMatter elearningMatter;
    private UserAccount userAccount;

	public ElearningCourse() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ElearningMatter getElearningMatter() {
		return this.elearningMatter;
	}

	public void setElearningMatter(ElearningMatter elearningMatter) {
		this.elearningMatter = elearningMatter;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isInstantCorrection() {
        return instantCorrection;
    }

    public void setInstantCorrection(boolean instantCorrection) {
        this.instantCorrection = instantCorrection;
    }

    public boolean isInstantCongratulation() {
        return instantCongratulation;
    }

    public void setInstantCongratulation(boolean instantCongratulation) {
        this.instantCongratulation = instantCongratulation;
    }

    public boolean isInstantSolution() {
        return instantSolution;
    }

    public void setInstantSolution(boolean instantSolution) {
        this.instantSolution = instantSolution;
    }

    public boolean getImportable() {
		return this.importable;
	}

	public void setImportable(boolean importable) {
		this.importable = importable;
	}

	public boolean getSecured() {
		return this.secured;
	}

	public void setSecured(boolean secured) {
		this.secured = secured;
	}

	public int getFreeSamples() {
		return freeSamples;
	}

	public void setFreeSamples(int freeSamples) {
		this.freeSamples = freeSamples;
	}

	public boolean isAutoSubscription() {
		return autoSubscription;
	}

	public void setAutoSubscription(boolean autoSubscription) {
		this.autoSubscription = autoSubscription;
	}

	public boolean isAutoUnsubscription() {
        return autoUnsubscription;
    }

    public void setAutoUnsubscription(boolean autoUnsubscription) {
        this.autoUnsubscription = autoUnsubscription;
    }

    public boolean getInterruptTimedOutExercise() {
		return this.interruptTimedOutExercise;
	}

	public void setInterruptTimedOutExercise(boolean interruptTimedOutExercise) {
		this.interruptTimedOutExercise = interruptTimedOutExercise;
	}

	public boolean getResetExerciseAnswers() {
		return this.resetExerciseAnswers;
	}

	public void setResetExerciseAnswers(boolean resetExerciseAnswers) {
		this.resetExerciseAnswers = resetExerciseAnswers;
	}

	public boolean getExerciseOnlyOnce() {
		return this.exerciseOnlyOnce;
	}

	public void setExerciseOnlyOnce(boolean exerciseOnlyOnce) {
		this.exerciseOnlyOnce = exerciseOnlyOnce;
	}

	public boolean isExerciseAnyOrder() {
		return exerciseAnyOrder;
	}

	public void setExerciseAnyOrder(boolean exerciseAnyOrder) {
		this.exerciseAnyOrder = exerciseAnyOrder;
	}

	public boolean getSaveResultOption() {
		return this.saveResultOption;
	}

	public void setSaveResultOption(boolean saveResultOption) {
		this.saveResultOption = saveResultOption;
	}

	public boolean getShuffleQuestions() {
		return this.shuffleQuestions;
	}

	public void setShuffleQuestions(boolean shuffleQuestions) {
		this.shuffleQuestions = shuffleQuestions;
	}

	public boolean getShuffleAnswers() {
		return this.shuffleAnswers;
	}

	public void setShuffleAnswers(boolean shuffleAnswers) {
		this.shuffleAnswers = shuffleAnswers;
	}

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

}
