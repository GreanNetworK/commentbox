/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.view;

import com.shipco.commentbox.utils.EmailUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author San
 */
@ManagedBean
@SessionScoped
public class IndexBean implements Serializable {

    private String mailMessage;
    private List<UploadedFile> uploadedFiles;

    @PostConstruct
    public void init() {
        uploadedFiles = new ArrayList<UploadedFile>();
    }

    public String getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(String mailMessage) {
        this.mailMessage = mailMessage;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void handleFileUpload(FileUploadEvent event) {
        uploadedFiles.add(event.getFile());
        popupMessage("Successful", event.getFile().getFileName() + " is uploaded.");
    }

    public void removeAttachment(UploadedFile uploadedFile) {
        uploadedFiles.remove(uploadedFile);
        popupMessage("Successful", uploadedFile.getFileName() + " is removed.");
    }

    public void send() {
        EmailUtils.sendCommentBoxMail(mailMessage, uploadedFiles);
        mailMessage = new String();
        uploadedFiles.clear();
        popupMessage("Successful", "mail has been sent.");
    }

    private void popupMessage(String summary, String detail) {
        FacesMessage msg = new FacesMessage(summary, detail);
        FacesContext.getCurrentInstance().addMessage("messages", msg);
    }
}
