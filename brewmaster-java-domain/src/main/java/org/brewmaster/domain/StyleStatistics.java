package org.brewmaster.domain;

public class StyleStatistics {
    private Range<Double> alcoholByVolume;

    public Range<Double> getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public void setAlcoholByVolume(Range<Double> alcoholByVolume) {
        this.alcoholByVolume = alcoholByVolume;
    }

    private Range<Integer> bitterness;

    public Range<Integer> getBitterness() {
        return bitterness;
    }

    public void setBitterness(Range<Integer> bitterness) {
        this.bitterness = bitterness;
    }

    private Range<Integer> color;

    public Range<Integer> getColor() {
        return color;
    }

    public void setColor(Range<Integer> color) {
        this.color = color;
    }

    private Range<Double> finalGravity;

    public Range<Double> getFinalGravity() {
        return finalGravity;
    }

    public void setFinalGravity(Range<Double> finalGravity) {
        this.finalGravity = finalGravity;
    }

    private Range<Double> originalGravity;

    public Range<Double> getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(Range<Double> originalGravity) {
        this.originalGravity = originalGravity;
    }
}
