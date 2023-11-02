package fr.hyu.niflheimMMO.hierarchy;

public class HierarchyProfile {
    public enum Hierarchy
    {
        PEASANT("PEASANT", "Paysan", "Paysanne"),
        ARTISANT("ARTISANT", "Artisant", "Artisanne"),
        KNIGHT("KNIGHT", "Chevalier", "Chevaleresse"),
        BARON("BARON", "Baron", "Baronne"),
        VISCOUNT("VISCOUNT", "Vicomte", "Vicomtesse"),
        COUNT("COUNT", "Comte", "Comtesse"),
        MARQUIS("MARQUIS", "Marquis", "Marquise"),
        DUKE("DUKE", "Duc", "Duchesse"),
        PRINCE("PRINCE", "Prince", "Princesse"),
        ARCHDUKE("ARCHDUKE", "Archiduc", "Archiduchesse"),
        ROYALTY("ROYALTY", "Roi", "Reine");

        private String name;
        private String displayNameM;
        private String displayNameF;

        private Hierarchy(String name, String displayNameM, String displayNameF) {

            this.name = name;
            this.displayNameM = displayNameM;
            this.displayNameF = displayNameF;
        }

        public String getName() {
            return this.name;
        }
        public String getDisplayNameM() {
            return this.displayNameM;
        }
        public String getDisplayNameF() {
            return this.displayNameF;
        }
    }
}
