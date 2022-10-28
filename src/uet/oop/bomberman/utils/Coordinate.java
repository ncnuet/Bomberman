package uet.oop.bomberman.utils;

import uet.oop.bomberman.Context;

public class Coordinate {
    private Point pixelCrd;   // pixel point
    private Point tileCrd; // tile point

    public Point getTileCrd() {
        return this.tileCrd;
    }

    public void setTileCrd(int x, int y) {
        this.tileCrd = new Point(x, y);
        this.pixelCrd = Convert.tileToPixel(this.tileCrd);
    }

    public Point getPixelCrd() {
        return this.pixelCrd;
    }

    public void setPixelCrd(int x, int y) {
        this.pixelCrd = new Point(x, y);
        this.tileCrd = Convert.pixelToTile(this.pixelCrd);
    }

    public void setX(int x) {
        this.tileCrd.setX(x);
        this.pixelCrd = Convert.tileToPixel(this.tileCrd);
    }

    public int getX() {
        return this.tileCrd.getX();
    }

    public int getY() {
        return this.tileCrd.getY();
    }

    public void setY(int y) {
        this.tileCrd.setY(y);
        this.pixelCrd = Convert.tileToPixel(this.tileCrd);
    }

    public void setXAsPixel(int x) {
        this.pixelCrd.setX(x);
        this.tileCrd = Convert.pixelToTile(this.pixelCrd);
    }

    public void setXAsPixel(int x, int offsetX, int offsetY) {
        this.pixelCrd.setX(x);
        this.tileCrd = Convert.pixelToTile(
                new Point(x + offsetX, this.getPixelCrd().getY() + offsetY));
    }

    public void setYAsPixel(int y) {
        this.pixelCrd.setY(y);
        this.tileCrd = Convert.pixelToTile(this.pixelCrd);
    }

    public void setYAsPixel(int y, int offsetX, int offsetY) {
        this.pixelCrd.setY(y);
        this.tileCrd = Convert.pixelToTile(
                new Point(this.getPixelCrd().getX() + offsetX, y + offsetY));
    }

    public Coordinate(int x, int y) {
        this.tileCrd = new Point(x, y);
        this.pixelCrd = Convert.tileToPixel(this.tileCrd);
    }

    public boolean isInMap(Context context) {
        Point tilePoint = this.tileCrd;
        return tilePoint.getX() >= 0
                && tilePoint.getY() >= 0
                && tilePoint.getX() <= context.getWidth() - 1
                && tilePoint.getY() <= context.getHeight() - 1;
    }

    public static Coordinate createCrdByTile(int x, int y) {
        return new Coordinate(x, y);
    }

    public static Coordinate createCrdByPixel(int x, int y) {
        Coordinate crd = new Coordinate(0, 0);
        crd.setPixelCrd(x, y);
        return crd;
    }

    /**
     * Compare coordinate
     *
     * @param crd coordinate
     * @return boolean
     */
    public boolean equals(Coordinate crd) {
        // ignore pixel value.
        return this.getTileCrd().equals(crd.getTileCrd());
    }

    /**
     * Deep compare value.
     *
     * @param crd coordinate
     * @return boolean
     */
    public boolean deepEquals(Coordinate crd) {
        // include pixel value.
        return this.getTileCrd().equals(crd.getTileCrd())
                && this.getPixelCrd().equals(crd.getPixelCrd());
    }

    @Override
    public String toString() {
        return this.getTileCrd().getX() + " " + this.getTileCrd().getY();
    }
}
