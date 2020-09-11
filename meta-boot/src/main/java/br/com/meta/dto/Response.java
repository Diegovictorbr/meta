package br.com.meta.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Response<E> {

    private int status;
    private String errorCode;
    private String stacktrace;
    private List<Message> messages;
    private List<E> data;

    public Response() {
    }

    private Response(Builder<E> builder) {
        super();
        this.status = builder.status;
        this.errorCode = builder.errorCode;
        this.stacktrace = builder.stacktrace;
        this.messages = builder.messages;
        this.data = builder.data;
    }

    public static <E> Builder<E> status(int status, Class<E> clazz) {
        return new Builder<E>(status);
    }

    public int getStatus() {
        return this.status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public List<E> getData() {
        return this.data;
    }

    public static final class Message implements Serializable {

        private String id;
        private String message;

        public Message() {
        }

        public Message(String id, String message) {
            this.id = id;
            this.message = message;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public final static class Builder<E> {

        private int status;
        private String errorCode;
        private String stacktrace;
        private Exception exception;
        private List<Message> messages = new ArrayList<>();
        private List<E> data = new ArrayList<E>();

        @SuppressWarnings("unused")
        private Builder() {
            super();
        }

        private Builder(int status) {
            this.status = status;
        }

        public Builder<E> exception(Exception exception) {
            this.exception = exception;
            return this;
        }

        public Builder<E> messages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public Builder<E> message(Message message) {
            this.messages.add(message);
            return this;
        }

        public Builder<E> message(String message) {
            this.messages.add(new Message(null, message));
            return this;
        }

        public Builder<E> message(String id, String message) {
            this.messages.add(new Message(id, message));
            return this;
        }

        public Builder<E> data(E data) {
            if (data != null)
                this.data.add(data);

            return this;
        }

        public Builder<E> data(List<E> data) {
            this.data.addAll(data);
            return this;
        }

        public ResponseEntity<Response<E>> build() {
            if (this.status >= 400)
                this.errorCode = generateErrorCode();

            return new ResponseEntity<Response<E>>(new Response<E>(this), HttpStatus.valueOf(this.status));
        }
    }

    private static String generateErrorCode() {
        Random rand = new Random(System.nanoTime());
        String errorCode = Long.toHexString(rand.nextLong()).toUpperCase();
        return errorCode;
    }
}