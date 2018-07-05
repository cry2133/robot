package com.robot.robot.controller.app.bean;

import java.util.List;

public class FaqRequestBean {
	
	private String answer;
	private String question;
	private List<String> questionList;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<String> questionList) {
		this.questionList = questionList;
	}
	
}
