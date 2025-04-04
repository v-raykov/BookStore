package com.viktor.oop.gui.main;

import com.viktor.oop.gui.web.get.all.AllBooksPane;
import com.viktor.oop.gui.web.post.CreateBookPane;
import com.viktor.oop.gui.web.put.EditPanel;

import javax.swing.*;

import java.awt.*;
import java.util.UUID;

public class MainPane extends JSplitPane {
    private final TopPanel topPanel;

    private final AllBooksPane allBooksPane;
    private final CreateBookPane createBookPane;
    private final EditPanel editPanel;

    private Regime regime;

    public MainPane() {
        setLayout(new BorderLayout());
        topPanel = new TopPanel();
        createBookPane = new CreateBookPane();
        editPanel = new EditPanel();
        allBooksPane = new AllBooksPane();
        regime = Regime.LIST;
        setListeners();
        updatePanel();
    }

    private void setListeners() {
        topPanel.setRepoSwitchListener(allBooksPane::switchRepo);
        topPanel.setRegimeListener(this::setRegime);
        editPanel.setRegimeListener(this::setRegime);
        allBooksPane.setEditListener(this::editBook);
    }

    private void editBook(UUID id) {
        editPanel.setBookById(id);
        setRegime(Regime.EDIT);
    }

    private void setRegime(Regime regime) {
        this.regime = regime;
        removeAll();
        updatePanel();
        revalidate();
        repaint();
    }

    private void updatePanel() {
        removeAll();
        add(topPanel, BorderLayout.NORTH);
        add(getPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    private JComponent getPanel() {
        return switch (regime) {
            case LIST -> refreshAndGetAllBooksPanel();
            case CREATE -> createBookPane;
            case EDIT -> editPanel;
        };
    }

    private JSplitPane refreshAndGetAllBooksPanel() {
        allBooksPane.refresh();
        return allBooksPane;
    }
}
