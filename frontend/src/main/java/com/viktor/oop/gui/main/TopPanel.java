package com.viktor.oop.gui.main;

import com.viktor.oop.gui.listener.RepoSwitchListener;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private boolean useDatabase = true;

    @Setter
    private RepoSwitchListener repoSwitchListener;

    public TopPanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(0, 40));

        var repoButton = createRepoButton();
        var createButton = createBookButton();

        add(repoButton);
        add(createButton);

        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
    }

    private JButton createRepoButton() {
        var repoButton = new JButton(getRepoButtonText());
        repoButton.addActionListener(_ -> toggleRepository(repoButton));
        return repoButton;
    }

    private JButton createBookButton() {
        return new JButton("Create book");
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
}
