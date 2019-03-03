#include <stdio.h>
#include <malloc.h>
#include <time.h>
#include <stdlib.h>

int counterMTD = 0;
int counterTRANS = 0;

/**
 * Struktura obsługująca listę.
 */
typedef struct list {
    int value;
    struct list* ptr;
} list;


/**
 * Funkcja sprawdza czy lista jest pusta.
 * @param l - wskażnik na pierwszy element listy
 * @return - zwraca 1 jeśli lista jest pusta, a przeciwnym wypadku 0
 */
int isEmpty(list* l) {
    if (l==NULL) {
        return 1;
    }
    return 0;
}

/**
 * Procedura dodająca element do listy.
 * @param l - wskaźnik na wskaźnik na pierwszy element listy
 * @param value - wartość dodawana do listy
 */
void insert(list **l, int value) {
    list* el = (list*)malloc(sizeof(list));     ////tworzenie nowego elementu
    el->value = value;
    el->ptr = *l;                               ////ustawienie elementu na pierwszej pozycji
    *l = el;
}

/**
 * Procedura wyświetlająca wartości elementów listy.
 * @param l - wskaźnik na pierwszy element listy
 */
void printList(list* l) {
    while(l != NULL) {
        printf("%d : ",l->value);
        l = l->ptr;
    }
    printf("X\n");
}

/**
 * Funkcja usuwająca element o danej wartości.
 * @param l - wskaźnik na wskaźnik na pierwszy element listy
 * @param value - wartość do usunięia
 * @return - zwraca 1 gdy pomyślnie usunięto i 0 gdy element nie został znaleziony
 */
int delete(list** l, int value) {
    list* t = *l;
    if(t->value==value) {                       ////usunięcie elementu jeśli znajduje sie na pierwszej pozycji
        *l = (*l)->ptr;
        free(t);
        return 1;
    } else {
        while (t->ptr != NULL && t->ptr->value != value) {
            t = t->ptr;
        }
        if (t->ptr != NULL) {                   ////usunięcie jeśli element został znaleziony
            list *temp = t->ptr;
            t->ptr = t->ptr->ptr;
            free(temp);
            return 1;
        } else {                                ////wartość 0 jeśli element nie został znaleziony
            return 0;
        }
    }
}

/**
 * Funkcja sprawdzająca czy dany element znajduje się w liście i przenosząca go na pierwszą pozycję.
 * @param l - wskaźnik na wskaźnik na pierwszy element listy
 * @param value - poszukiwana wartość
 * @return - zwraca 1 jeśli element znajduje się w liście
 */
int findMTD(list** l, int value) {
    list* t = *l;
    if (t->value==value) {                      ////jeśli element jest już na pierwszy miejscu zwróc 1
        counterMTD++;
        return 1;
    } else {
        while (t->ptr!=NULL && t->ptr->value != value) {
            counterMTD++;
            t = t->ptr;
        }
        if (t->ptr != NULL) {                   ////jeśli element został znaleziony umieść go na pierwszym miejscu
            counterMTD++;
            list* temp = t->ptr;
            t->ptr = t->ptr->ptr;
            temp->ptr = *l;
            *l = temp;
            return 1;
        } else {                                ////zwróc 0 jeśli element nie został znaleziony
            return 0;
        }
    }
}

/**
 * Funkcja sprawdza czy dany element znajduje się na liście i przenosząca go o jedną pozycję dalej.
 * @param l - wskaźnik na wskaźnik na pierwszy element listy
 * @param value - poszukiwana wartość
 * @return - zwraca 1 jeśli element znajduje się w liście
 */
int findTRANS(list** l, int value) {
    list* t = *l;
    if (t->value==value) {                      ////przesuwanie elementu jeśli jest na pierwszy miejscu w liście
        counterTRANS++;
        if(t->ptr == NULL) {                    ////jeśli tabela ma jeden element nie trzeba nic robić
            counterTRANS++;
            return 1;
        } else {                                ////przesunięcie elementu jeśli wielkość tabeli większa niż 1
            list* temp = t->ptr;
            t->ptr = t->ptr->ptr;
            temp->ptr = t;
            *l = temp;
            return 1;
        }
    } else {
        while (t->ptr!=NULL && t->ptr->value != value) {
            counterTRANS++;
            t = t->ptr;
        }
        if (t->ptr != NULL) {                   ////jeśli element został znaleziony
            counterTRANS++;
            if (t->ptr->ptr == NULL) {          ////jeśli element jest ostatnim elementem nie trzeba go przesuwać
                counterTRANS++;
                return 1;
            } else {                            ////jeśli element nie jest ostatni wykonaj transpozycję
                list *temp = t->ptr;
                t->ptr = t->ptr->ptr;
                temp->ptr = t->ptr->ptr;
                t->ptr->ptr = temp;
                return 1;
            }
        } else {                                ////jeśli element nie został znaleziony zwróć zero
            return 0;
        }
    }
}

/**
 * Funkcja zwraca tabelę zapełnioną losowymi wartościami z zakresu 1-100
 * @return - tabela z losowymi wartościami
 */
int* randTab() {
    srand((unsigned int) time(NULL)); // NOLINT(cert-msc32-c,cert-msc51-cpp)
    int p1, p2, temp;
    int* tab = malloc(100*sizeof(int));
    for (int i=0; i<100; i++) {
        tab[i] = i+1;
    }
    for (int i=100; i>0; i--) {
        p1 = (int) (random() % 100);
        p2 = (int) (random() % 100);

        temp = tab[p1];
        tab[p1] = tab[p2];
        tab[p2] = temp;
    }
    return tab;
}

int main(void) {
    list* head = NULL;
    int* tab = randTab();
    for (int i=0; i<100; i++) {
        insert(&head, tab[i]);
    }
    printList(head);
    for (int i=100; i>0; i--) {
        findTRANS(&head, i);
        delete(&head, i);
        printList(head);
    }
    tab = randTab();
    for (int i=0; i<100; i++) {
        insert(&head, tab[i]);
    }
    printList(head);
    printf("%s\n", isEmpty(head)?"empty":"not empty");
    for (int i=100; i>0; i--) {
        findMTD(&head, i);
        delete(&head, i);
        printList(head);
    }
    printf("%s\n", isEmpty(head)?"empty":"not empty");
    printf("Liczba porównać przy szukaniu MTD: %d\n", counterMTD);
    printf("Liczba porównać przy szukaniu TRANS: %d", counterTRANS);
    return 0;
}
