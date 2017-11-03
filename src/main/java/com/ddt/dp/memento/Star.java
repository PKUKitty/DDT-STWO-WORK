/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.memento;

public class Star {

    private StarType type;

    private int ageYears;

    private int massTons;


    public Star(StarType type, int ageYears, int massTons) {
        this.type = type;
        this.ageYears = ageYears;
        this.massTons = massTons;
    }

    public void timePasses() {
        ageYears *= 2;
        massTons *= 8;

        switch (type) {
            case RED_GIANT:
                type = StarType.WHITE_DWARF;
                break;
            case SUN:
                type = StarType.RED_GIANT;
                break;
            case SUPERNOVA:
                type = StarType.DEAD;
                break;
            case WHITE_DWARF:
                type = StarType.SUPERNOVA;
                break;
            case DEAD:
                ageYears *= 2;
                massTons *= 8;
                break;
            default:
                break;
        }
    }

    StarMemento getMemento() {
        StarMementoInternal stat = new StarMementoInternal();
        stat.setAgeYears(ageYears);
        stat.setMassTons(massTons);
        stat.setType(type);
        return stat;
    }

    void setMemento(StarMemento memento) {
        StarMementoInternal stat = (StarMementoInternal) memento;
        this.ageYears = stat.getAgeYears();
        this.massTons = stat.getMassTons();
        this.type = stat.getType();
    }

    private static class StarMementoInternal implements StarMemento {
        private StarType type;
        private int ageYears;
        private int massTons;

        public StarType getType() {
            return type;
        }

        public void setType(StarType type) {
            this.type = type;
        }

        public int getAgeYears() {
            return ageYears;
        }

        public void setAgeYears(int ageYears) {
            this.ageYears = ageYears;
        }

        public int getMassTons() {
            return massTons;
        }

        public void setMassTons(int massTons) {
            this.massTons = massTons;
        }
    }
}
