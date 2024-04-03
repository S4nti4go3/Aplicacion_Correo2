package com.example.aplicacion_correo;
import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable {
    private String sender;
    private String subject;
    private String content;
    private String date;
    private String imageUrl;
    private boolean isRead;
    private int id;

    public Email(int id, String sender, String subject, String content, String date) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.date = date;
        this.isRead = false;
        this.id = id;
    }

    protected Email(Parcel in) {
        sender = in.readString();
        subject = in.readString();
        content = in.readString();
        date = in.readString();
        imageUrl = in.readString();
        isRead = in.readByte() != 0;
        id = in.readInt();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel in) {
            return new Email(in);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sender);
        dest.writeString(subject);
        dest.writeString(content);
        dest.writeString(date);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (isRead ? 1 : 0));
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}