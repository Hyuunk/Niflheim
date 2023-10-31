package fr.hyu.niflheimMMO.hierarchy;

public class HierarchyProfile {
    public enum Hierarchy
    {
        PEASANT(),
        ARTISANT(),
        KNIGHT(),
        BARON(),
        VISCOUNT(),
        COUNT(),
        MARQUIS(),
        DUKE(),
        PRINCE(),
        ARCHDUKE(),
        ROYALTY();

        private String name;

        private Hierarchy() {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
