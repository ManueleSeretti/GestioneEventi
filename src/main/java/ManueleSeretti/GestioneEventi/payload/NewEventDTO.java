package ManueleSeretti.GestioneEventi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEventDTO(@NotEmpty(message = "Il titolo è un campo obbligatorio!")
                          String title,
                          @NotEmpty(message = "La descrizione è un campo obbligatorio!")
                          String description,
                          @NotEmpty(message = "La location è un campo obbligatorio!")
                          String location,

                          LocalDate data,
                          @NotNull(message = "il numero di posti per l'evento è obbligatorio")
                          int posti) {
}
