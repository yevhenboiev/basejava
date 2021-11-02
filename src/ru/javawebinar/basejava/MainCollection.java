package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MainCollection {
    static Resume r1 = new Resume("UUID1", "Pypi Igor");
    static Resume r2 = new Resume("UUID2", "Pypok Igor");
    static Resume r3 = new Resume("UUID3", "Pypkin Igor");

    public static void main(String[] args) {
        Collection<Resume> resume = new ArrayList<>();
        resume.add(r1);
        resume.add(r2);
        resume.add(r3);
        System.out.println(resume);
        Iterator<Resume> iterator = resume.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (r.getUuid().equals("UUID2")) {
                iterator.remove();
            }
        }
        System.out.println(resume);

        Map<String, Resume> map = new HashMap<>();
        map.put("UUID1", r1);
        map.put("UUID2", r2);
        map.put("UUID3", r3);

        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}