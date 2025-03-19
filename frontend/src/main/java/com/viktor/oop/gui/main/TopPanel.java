package com.viktor.oop.gui.main;

import com.viktor.oop.gui.listener.RegimeListener;
import com.viktor.oop.gui.listener.RepoSwitchListener;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private boolean useDatabase = true;
    private boolean createMode = false;

    @Setter
    private RepoSwitchListener repoSwitchListener;

    @Setter
    private RegimeListener booksRegimeListener;

    public TopPanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(0, 40));
        var repoButton = createRepoButton();
        var createButton = bookRegimeButton();
        add(repoButton);
        add(createButton);
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
    }

    private JButton createRepoButton() {
        var repoButton = new JButton(getRepoButtonText());
        repoButton.addActionListener(_ -> toggleRepository(repoButton));
        return repoButton;
    }

    private void toggleRepository(JButton repoButton) {
        useDatabase = !useDatabase;
        repoButton.setText(getRepoButtonText());
        if (repoSwitchListener != null) {
            repoSwitchListener.switchRepo(useDatabase);
        }
    }

    private String getRepoButtonText() {
        return useDatabase ? "In-Memory Repository" : "Database Repository";
    }

    private JButton bookRegimeButton() {
        var regimeButton =  new JButton("Create book");
        regimeButton.addActionListener(_ -> toggleRegime(regimeButton));
        return regimeButton;
    }

    private void toggleRegime(JButton regimeButton) {
        createMode = !createMode;
        regimeButton.setText(getRegimeButtonText());
        if (booksRegimeListener != null) {
            booksRegimeListener.switchRegime(createMode ? Regime.CREATE : Regime.LIST);
        }
    }

    private String getRegimeButtonText() {
        return createMode ? "List books" : "Create book";
    }
}
