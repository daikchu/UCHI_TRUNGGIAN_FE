package com.vn.osp.modelview;

import java.util.ArrayList;

/**
 * Created by tranv on 3/17/2017.
 */
public class HomeForm {
    private Announcement latest;
    private ArrayList<Announcement> announcementArrayList;

    private int daTiepNhan;
    private int daTrinhLdao;
    private int dangXly;
    private int daDuyet;
    private int khongDuyet;
    private long hoSoCongChung;

    public HomeForm() {
    }

    public Announcement getLatest() {
        return latest;
    }

    public void setLatest(Announcement latest) {
        this.latest = latest;
    }

    public ArrayList<Announcement> getAnnouncementArrayList() {
        return announcementArrayList;
    }

    public void setAnnouncementArrayList(ArrayList<Announcement> announcementArrayList) {
        this.announcementArrayList = announcementArrayList;
    }

    public int getDaTiepNhan() {
        return daTiepNhan;
    }

    public void setDaTiepNhan(int daTiepNhan) {
        this.daTiepNhan = daTiepNhan;
    }

    public int getDaTrinhLdao() {
        return daTrinhLdao;
    }

    public void setDaTrinhLdao(int daTrinhLdao) {
        this.daTrinhLdao = daTrinhLdao;
    }

    public int getDangXly() {
        return dangXly;
    }

    public void setDangXly(int dangXly) {
        this.dangXly = dangXly;
    }

    public int getDaDuyet() {
        return daDuyet;
    }

    public void setDaDuyet(int daDuyet) {
        this.daDuyet = daDuyet;
    }

    public int getKhongDuyet() {
        return khongDuyet;
    }

    public void setKhongDuyet(int khongDuyet) {
        this.khongDuyet = khongDuyet;
    }

    public long getHoSoCongChung() {
        return hoSoCongChung;
    }

    public void setHoSoCongChung(long hoSoCongChung) {
        this.hoSoCongChung = hoSoCongChung;
    }
}
