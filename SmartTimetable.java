class Subject {
    String name;
    String teacher;
    String room;

    public Subject(String name, String teacher, String room) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
    }
}

public class SmartTimetable {

    static Subject[] subjects;
    static String[] timeSlots;

    static int[] timetable;

    public static void main(String[] args) {

        subjects = new Subject[] {
            new Subject("Math", "Mr. A", "R1"),
            new Subject("Physics", "Mr. B", "R2"),
            new Subject("Chemistry", "Mr. A", "R3"),
            new Subject("Biology", "Mr. C", "R1")
        };

        timeSlots = new String[] {
            "9AM", "10AM", "11AM", "12PM"
        };

        timetable = new int[subjects.length];

    
        for (int i = 0; i < timetable.length; i++) {
            timetable[i] = -1;
        }

        if (generateTimetable(0)) {
            System.out.println("Valid Timetable Generated:\n");
            printTimetable();
        } else {
            System.out.println("No valid timetable found.");
        }
    }

    
    public static boolean generateTimetable(int subjectIndex) {

    
        if (subjectIndex == subjects.length) {
            return true;
        }

        
        for (int slot = 0; slot < timeSlots.length; slot++) {

            if (isSafe(subjectIndex, slot)) {

                
                timetable[subjectIndex] = slot;

    
                if (generateTimetable(subjectIndex + 1)) {
                    return true;
                }

            
                timetable[subjectIndex] = -1;
            }
        }

        return false;
    }

    
    public static boolean isSafe(int current, int slot) {

        for (int i = 0; i < current; i++) {

    
            if (timetable[i] == slot) {

        
                if (subjects[i].teacher.equals(subjects[current].teacher)) {
                    return false;
                }

                if (subjects[i].room.equals(subjects[current].room)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printTimetable() {
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(
                subjects[i].name + " (" + subjects[i].teacher + ", " + subjects[i].room + ") -> " +
                timeSlots[timetable[i]]
            );
        }
    }
}