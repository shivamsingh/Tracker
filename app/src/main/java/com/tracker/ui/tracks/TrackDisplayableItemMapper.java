package com.tracker.ui.tracks;

import android.support.annotation.NonNull;

import com.tracker.data.tracker.Track;
import com.tracker.ui.DisplayableTypes;
import com.tracker.ui.recyclerview.DisplayableItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.tracker.ui.recyclerview.DisplayableItem.toDisplayableItem;

class TrackDisplayableItemMapper implements Function<List<Track>, List<DisplayableItem>> {

    private TrackViewEntityMapper trackViewEntityMapper;

    @Inject
    TrackDisplayableItemMapper(@NonNull final TrackViewEntityMapper trackViewEntityMapper) {
        this.trackViewEntityMapper = trackViewEntityMapper;
    }

    @Override
    public List<DisplayableItem> apply(List<Track> tracks) throws Exception {
        return Observable
                .fromIterable(tracks)
                .map(trackViewEntityMapper)
                .map(this::wrapInDisplayableItem)
                .toList()
                .blockingGet();
    }

    private DisplayableItem wrapInDisplayableItem(TrackViewEntity viewEntity) {
        return toDisplayableItem(viewEntity, DisplayableTypes.TRACK_ITEM);
    }
}
