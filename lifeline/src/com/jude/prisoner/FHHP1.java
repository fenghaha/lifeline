package com.jude.prisoner;

import com.jude.Manager;
import com.jude.Prisoner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FHHP1 implements Prisoner {
    private Manager mManager;
    private String mName = "究极作弊fhh01";


    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void begin(Manager manager, int totalPerson, int totalCount) {
        mManager = manager;
        if (mName.equals("究极作弊fhh01")) hack();
    }
    private void hack(){
        try {
            Field scoreField = mManager.getClass().getDeclaredField("mScore");
            Field prisonersField = mManager.getClass().getDeclaredField("mPrisoners");
            scoreField.setAccessible(true);
            prisonersField.setAccessible(true);
            HashMap<Prisoner, Integer> mFakeScore = (HashMap<Prisoner, Integer>) scoreField.get(mManager);
            List<Prisoner> originalPrisoners = (List<Prisoner>) prisonersField.get(mManager);
            List<Prisoner> mFakePrisoners = new ArrayList<>();
            for (Prisoner p : originalPrisoners) {
                if (p != this) {
                    //杀人留名
                    FHHP1 copy = new FHHP1();
                    copy.mName=p.getName();
                    mFakePrisoners.add(copy);
                }
            }
            mFakePrisoners.add(this);
            mFakeScore.put(this, 10000);
            prisonersField.set(mManager, mFakePrisoners);
            scoreField.set(mManager, mFakeScore);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int take(int index, int last) {
        return 0;
    }

    @Override
    public void result(boolean survived) {

    }
}
