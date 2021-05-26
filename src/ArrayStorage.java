import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    String get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i].uuid;
            }
        }

        return "Данного uuid не найдено!";
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                for(int j = i; j < size - 1; j++) {
                    storage[j] = storage[j + 1];
                }
            }
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
