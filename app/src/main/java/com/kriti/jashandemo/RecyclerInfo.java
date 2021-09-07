package com.kriti.jashandemo;

public class RecyclerInfo
{
    int rImage; boolean rHeart = false; String rText = "";
    int[] images = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};
    String[] text = {"One", "Two", "Three", "Four", "Five", "Six"};

    public int getrImage() {
        return rImage;
    }

    public void setrImage(int index) {
        this.rImage = images[index];
    }

    public boolean isrHeart() {
        return rHeart;
    }

    public void setrHeart(boolean rHeart) {
        this.rHeart = rHeart;
    }

    public String getrText() {
        return rText;
    }

    public void setrText(int index) {
        this.rText = text[index];
    }
}
