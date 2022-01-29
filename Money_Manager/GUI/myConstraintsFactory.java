package GUI;

import java.awt.*;

public class myConstraintsFactory {

    public static GridBagConstraints getGridBagConstraints(int gridx, int gridy, float weightx, float weighty, int anchor, int fill){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.fill = fill;
        return gridBagConstraints;
    }

}
