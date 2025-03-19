package com.viktor.oop.gui.main;

import com.viktor.oop.gui.web.get.all.AllBooksPane;
import com.viktor.oop.gui.web.post.CreateBookPane;
import com.viktor.oop.gui.web.put.EditPane;

import javax.swing.*;

import java.awt.*;
import java.util.UUID;

public class MainPane extends JSplitPane {
    private final TopPanel topPanel;

    private final AllBooksPane allBooksPane;
    private final CreateBookPane createBookPane;
    private final EditPane editBookPanel;

    private Regime regime;

    public MainPane() {
        setLayout(new BorderLayout());
        topPanel = new TopPanel();
        createBookPane = new CreateBookPane();
        editBookPanel = new EditPane();
        allBooksPane = new AllBooksPane();
        regime = Regime.LIST;
        setListeners();
        updatePanel();
    }

    private void setListeners() {
        topPanel.setRepoSwitchListener(allBooksPane::switchRepo);
        topPanel.setBooksRegimeListener(this::setRegime);
        editBookPanel.setRegimeListener(this::setRegime);
        allBooksPane.setEditListener(this::editBook);
    }

    private void editBook(UUID id) {
        editBookPanel.setBookId(id);
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
            case EDIT -> editBookPanel;
        };
    }

    private JSplitPane refreshAndGetAllBooksPanel() {
        allBooksPane.refresh();
        return allBooksPane;
    }
}
