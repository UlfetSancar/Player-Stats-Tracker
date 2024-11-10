//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PlayerInfoSystem extends JFrame {
    private final JTextField playerNameField;
    private final JTextField winField;
    private final JTextField killsField;
    private final JTextField deathsField;
    private final JTextField assistsField;
    private final JTextField topPlayedField;
    private final JTextField jungPlayedField;
    private final JTextField midPlayedField;
    private final JTextField adcPlayedField;
    private final JTextField supPlayedField;
    private final JTextField champsNameField;
    private final String playersDirectoryPath = "Players";

    public PlayerInfoSystem() {
        this.setTitle("PlayerInfoSystem");
        this.setSize(400, 700);
        this.setDefaultCloseOperation(3);
        this.setLayout(new GridLayout(0, 1));
        this.add(new JLabel("Player Name:"));
        this.playerNameField = new JTextField();
        this.add(this.playerNameField);
        this.add(new JLabel("Win (1 for win, 0 for loss):"));
        this.winField = new JTextField("0");
        this.add(this.winField);
        this.add(new JLabel("Kills:"));
        this.killsField = new JTextField("0");
        this.add(this.killsField);
        this.add(new JLabel("Deaths:"));
        this.deathsField = new JTextField("0");
        this.add(this.deathsField);
        this.add(new JLabel("Assists:"));
        this.assistsField = new JTextField("0");
        this.add(this.assistsField);
        this.add(new JLabel("Roles Played (1 for played, 0 otherwise):"));
        this.add(new JLabel("Top - Played:"));
        this.topPlayedField = new JTextField("0");
        this.add(this.topPlayedField);
        this.add(new JLabel("Jung - Played:"));
        this.jungPlayedField = new JTextField("0");
        this.add(this.jungPlayedField);
        this.add(new JLabel("Mid - Played:"));
        this.midPlayedField = new JTextField("0");
        this.add(this.midPlayedField);
        this.add(new JLabel("Adc - Played:"));
        this.adcPlayedField = new JTextField("0");
        this.add(this.adcPlayedField);
        this.add(new JLabel("Sup - Played:"));
        this.supPlayedField = new JTextField("0");
        this.add(this.supPlayedField);
        this.add(new JLabel("Champs name (comma separated):"));
        this.champsNameField = new JTextField();
        this.add(this.champsNameField);
        JButton saveButton = new JButton("Save Info");
        saveButton.addActionListener(new SaveButtonListener());
        this.add(saveButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerInfoSystem frame = new PlayerInfoSystem();
            frame.setVisible(true);
        });
    }

    private class SaveButtonListener implements ActionListener {
        private SaveButtonListener() {
        }

        public void actionPerformed(ActionEvent e) {
            String playerName = PlayerInfoSystem.this.playerNameField.getText().trim();
            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog((Component)null, "Player Name is required.");
            } else {
                int win = Integer.parseInt(PlayerInfoSystem.this.winField.getText().trim());
                int kills = Integer.parseInt(PlayerInfoSystem.this.killsField.getText().trim());
                int deaths = Integer.parseInt(PlayerInfoSystem.this.deathsField.getText().trim());
                int assists = Integer.parseInt(PlayerInfoSystem.this.assistsField.getText().trim());
                int topPlayed = Integer.parseInt(PlayerInfoSystem.this.topPlayedField.getText().trim());
                int jungPlayed = Integer.parseInt(PlayerInfoSystem.this.jungPlayedField.getText().trim());
                int midPlayed = Integer.parseInt(PlayerInfoSystem.this.midPlayedField.getText().trim());
                int adcPlayed = Integer.parseInt(PlayerInfoSystem.this.adcPlayedField.getText().trim());
                int supPlayed = Integer.parseInt(PlayerInfoSystem.this.supPlayedField.getText().trim());
                Map<String, Integer> championCounts = new HashMap();
                String[] var13 = PlayerInfoSystem.this.champsNameField.getText().trim().split(",");
                int var14 = var13.length;

                int totalWins;
                for(totalWins = 0; totalWins < var14; ++totalWins) {
                    String champ = var13[totalWins];
                    if (!champ.isBlank()) {
                        champ = champ.trim();
                        championCounts.put(champ, (Integer)championCounts.getOrDefault(champ, 0) + 1);
                    }
                }

                File playersDirectory = new File("Players");
                if (!playersDirectory.exists()) {
                    playersDirectory.mkdir();
                }

                File playerFile = new File("Players/" + playerName + ".txt");
                totalWins = 0;
                int totalLosses = 0;
                int cumulativeKills = 0;
                int cumulativeDeaths = 0;
                int cumulativeAssists = 0;
                double totalKDA = 0.0;
                int topTotal = 0;
                int jungTotal = 0;
                int midTotal = 0;
                int adcTotal = 0;
                int supTotal = 0;
                if (playerFile.exists()) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(playerFile));

                        String line;
                        try {
                            while((line = reader.readLine()) != null) {
                                if (line.startsWith("Wins:")) {
                                    totalWins = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Losses:")) {
                                    totalLosses = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Kills:")) {
                                    cumulativeKills = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Deaths:")) {
                                    cumulativeDeaths = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Assists:")) {
                                    cumulativeAssists = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Total KDA:")) {
                                    totalKDA = Double.parseDouble(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Top - Played:")) {
                                    topTotal = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Jung - Played:")) {
                                    jungTotal = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Mid - Played:")) {
                                    midTotal = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Adc - Played:")) {
                                    adcTotal = Integer.parseInt(line.split(": ")[1].trim());
                                }

                                if (line.startsWith("Sup - Played:")) {
                                    supTotal = Integer.parseInt(line.split(": ")[1].trim());
                                }
                            }
                        } catch (Throwable var44) {
                            try {
                                reader.close();
                            } catch (Throwable var40) {
                                var44.addSuppressed(var40);
                            }

                            throw var44;
                        }

                        reader.close();
                    } catch (IOException var45) {
                        IOException ex = var45;
                        JOptionPane.showMessageDialog((Component)null, "Error reading player info: " + ex.getMessage());
                        return;
                    }
                }

                totalWins += win;
                totalLosses += 1 - win;
                int var10000 = cumulativeKills + kills;
                var10000 = cumulativeDeaths + deaths;
                var10000 = cumulativeAssists + assists;
                topTotal += topPlayed;
                jungTotal += jungPlayed;
                midTotal += midPlayed;
                adcTotal += adcPlayed;
                supTotal += supPlayed;
                double winRate = totalWins + totalLosses > 0 ? (double)totalWins / (double)(totalWins + totalLosses) * 100.0 : 0.0;
                String mostPlayedRole = this.determineMostPlayedRole(topTotal, jungTotal, midTotal, adcTotal, supTotal);
                double currentKDA = deaths > 0 ? ((double)kills + (double)assists) / (double)deaths : (double)(kills + assists);
                totalKDA += currentKDA;
                StringBuilder championsData = new StringBuilder();
                Iterator var33 = championCounts.entrySet().iterator();

                String timestamp;
                while(var33.hasNext()) {
                    Map.Entry<String, Integer> entry = (Map.Entry)var33.next();
                    timestamp = (String)entry.getKey();
                    int count = (Integer)entry.getValue();
                    if (count > 1) {
                        championsData.append(count).append("x ").append(timestamp).append(", ");
                    } else {
                        championsData.append(timestamp).append(", ");
                    }
                }

                if (championsData.length() > 0) {
                    championsData.setLength(championsData.length() - 2);
                }

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                timestamp = now.format(formatter);
                StringBuilder playerData = new StringBuilder();
                playerData.append("=====================================\n");
                playerData.append("Last Updated: ").append(timestamp).append("\n");
                playerData.append("=====================================\n");
                playerData.append("Player Name: ").append(playerName).append("\n");
                playerData.append("=====================================\n");
                playerData.append("Wins: ").append(totalWins).append("\n");
                playerData.append("Losses: ").append(totalLosses).append("\n");
                playerData.append("Winrate: ").append(String.format("%.2f", winRate)).append("%\n");
                playerData.append("=====================================\n");
                playerData.append("KDA: ").append(String.format("%.2f", currentKDA)).append(" (Kills: ").append(kills).append(", Deaths: ").append(deaths).append(", Assists: ").append(assists).append(")\n");
                playerData.append("Total KDA: ").append(String.format("%.2f", totalKDA)).append("\n");
                playerData.append("=====================================\n");
                playerData.append("Top - Played: ").append(topTotal).append("\n");
                playerData.append("Jung - Played: ").append(jungTotal).append("\n");
                playerData.append("Mid - Played: ").append(midTotal).append("\n");
                playerData.append("Adc - Played: ").append(adcTotal).append("\n");
                playerData.append("Sup - Played: ").append(supTotal).append("\n");
                playerData.append("Most Played Role: ").append(mostPlayedRole).append("\n");
                playerData.append("=====================================\n");
                playerData.append("Champs name: ").append(championsData.toString()).append("\n");

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(playerFile));

                    try {
                        writer.write(playerData.toString());
                        JOptionPane.showMessageDialog((Component)null, "Player info saved/updated in " + playerFile.getName());
                    } catch (Throwable var42) {
                        try {
                            writer.close();
                        } catch (Throwable var41) {
                            var42.addSuppressed(var41);
                        }

                        throw var42;
                    }

                    writer.close();
                } catch (IOException var43) {
                    IOException ioException = var43;
                    JOptionPane.showMessageDialog((Component)null, "Error saving player info: " + ioException.getMessage());
                }

            }
        }

        private String determineMostPlayedRole(int top, int jung, int mid, int adc, int sup) {
            int maxPlayed = Math.max(top, Math.max(jung, Math.max(mid, Math.max(adc, sup))));
            if (maxPlayed == top) {
                return "Top";
            } else if (maxPlayed == jung) {
                return "Jung";
            } else if (maxPlayed == mid) {
                return "Mid";
            } else {
                return maxPlayed == adc ? "Adc" : "Sup";
            }
        }
    }
}
