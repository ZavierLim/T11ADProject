package sg.edu.iss.ad.model;

public class MailVo {

    public MailVo(){

    }


    //sent from whom
    private String from;

    public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	//sent to whom
    private String to;
	//title
    private String subject;
    //content
    private String text;
    
	public MailVo(String from, String to, String subject, String text) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
    
    
}