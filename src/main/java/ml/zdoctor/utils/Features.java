package ml.zdoctor.utils;

import ml.zdoctor.API.API;

/**
 * @Author >> zDoctor_
 * Date >> 8/3/2021
 **/

public class Features {

    public enum Feature {
        AGE, GENDER, POLICE, AMBULANCE, HANDCUFFS, MASK, RECEIPT, INVSEE, DISABLEPVP, BROADCAST
    }

    public static boolean isEnabled(Feature feature) {
        boolean enabled = false;
        switch (feature) {
            case AGE:
                if (API.getSettingBoolean("age.enabled")) enabled = true;
                break;
            case MASK:
                if (API.getSettingBoolean("mask.enabled")) enabled = true;
                break;
            case GENDER:
                if (API.getSettingBoolean("gender.enabled")) enabled = true;
                break;
            case INVSEE:
                if (API.getSettingBoolean("invsee.enabled")) enabled = true;
                break;
            case POLICE:
                if (API.getSettingBoolean("police.enabled")) enabled = true;
                break;
            case RECEIPT:
                if (API.getSettingBoolean("receipt.enabled")) enabled = true;
                break;
            case AMBULANCE:
                if (API.getSettingBoolean("ambulance.enabled")) enabled = true;
                break;
            case HANDCUFFS:
                if (API.getSettingBoolean("handcuffs.enabled")) enabled = true;
                break;
            case DISABLEPVP:
                if (API.getSettingBoolean("disable-player-pvp.enabled")) enabled = true;
                break;
        }
        return enabled;
    }
}
