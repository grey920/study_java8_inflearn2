package me.whiteship.java8to11;

import java.util.Optional;

public class OnlineClass {
    private Integer id;

    private String title;

    private boolean closed;

    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Optional<Progress> getProgress () {
        return Optional.empty();
        //return Optional.ofNullable( progress ); // 문법적인 제한은 없지만 return에만 쓰는것이 좋다
    }

    public void setProgress ( Optional<Progress> progress ) {
        if ( progress!= null ) {
            progress.ifPresent( p -> this.progress = p );
        }
    }
}
