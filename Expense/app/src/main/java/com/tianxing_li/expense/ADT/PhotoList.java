package com.tianxing_li.expense.adt;

public class PhotoList {
    private int size;
    private Photo[] list;

    public PhotoList() {
        this.list = new Photo[3];
        this.list[0] = new Photo();
        this.size = 1;
    }

    public int size() {
        return this.size;
    }

    public Photo get(int position) {
        return this.list[position];
    }

    public void add(Photo p) {
        switch (size) {
            case 1:
                list[0] = p;
                list[1] = new Photo();
                size++;
                break;
            case 2:
                list[1] = p;
                list[2] = new Photo();
                size++;
                break;
            case 3:
                list[2] = p;
                break;
        }
    }

    public void add(int position, Photo p) {

    }

    public void remove(int position) {
        switch (position) {
            case 2:
                list[position] = new Photo();
                break;
            case 1:
                list[position] = list[position + 1];
                if (list[position].getStatus()==Photo.NEW) {
                    list[position + 1] = null;
                    size--;
                }
                else
                    list[position+1] = new Photo();
                break;
            case 0:
                if (list[position+1].getStatus()==Photo.NEW) {
                    list[position] = new Photo();
                    list[position+1] = null;
                    size--;
                } else {
                    for (int i=0; i<2; i++)
                        list[i]=list[i+1];
                    if (list[2].getStatus()==Photo.NEW) {
                        size--;
                    }
                    else
                        list[2] = new Photo();
                }
        }
    }
}
