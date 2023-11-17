package ManueleSeretti.GestioneEventi.payload;

import jakarta.validation.constraints.NotNull;

public record NewPartecipationDTO(@NotNull(message = "Il titolo è un campo obbligatorio!")
                                  long eventId,
                                  @NotNull(message = "La descrizione è un campo obbligatorio!")
                                  long userId) {
}
